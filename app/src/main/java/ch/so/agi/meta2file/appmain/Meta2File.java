package ch.so.agi.meta2file.appmain;


import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.db.TpIterator;
import ch.so.agi.meta2file.libmain.Environment;
import ch.so.agi.meta2file.out.MetaBean2FileConverter;
import ch.so.agi.meta2file.out.geocat.Geocat;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

public class Meta2File {
    private static final Character C_CONNECTION = 'c';
    private static final Character U_USER = 'u';
    private static final Character P_PASSWORD = 'p';
    private static final Character D_DATA_DOWNLOAD_APP = 'd';
    private static final Character G_GEOCAT_FOLDER = 'g';
    private static final Character H_HELP = 'h';
    private static final String H_HELP_LONG = "help";
    private static final Character E_ENV = 'e';

    private static Logger log = LoggerFactory.getLogger(Meta2File.class);

    public static Environment environment;

    public static void main(String[] args) throws Exception {

        int res = mainWithExitCode(args);
        if(res != 0)
            System.exit(res);
    }

    static int mainWithExitCode(String[] args){
        int res = 0;

        try{
            Options opt = initOptions();

            if(args == null || args.length == 0 || helpOptionPresent(args)){
                printHelp(opt);
                return 0;
            }

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(opt, args);

            environment = Environment.forIdentifier(cmd.getOptionValue(E_ENV));
            log.info("Set environment to " + environment);

            if(cmd.hasOption(D_DATA_DOWNLOAD_APP)){
                File destFile = new File(cmd.getOptionValue(D_DATA_DOWNLOAD_APP));
                createFile(destFile);

                try(Connection con = createCon(cmd)){
                    exportAppFile(con, destFile);
                }

                log.info("Finished exporting to file {}.", destFile);
            }
            else if (cmd.hasOption(G_GEOCAT_FOLDER)){
                File destFolder = new File(cmd.getOptionValue(G_GEOCAT_FOLDER));
                createFolder(destFolder);

                try(Connection con = createCon(cmd)){
                    exportGeocatFiles(con, destFolder);
                }

                log.info("Finished exporting into folder {}.", destFolder);
            }
            else {
                System.out.println("Please define option -d or -g for the export destination");
            }
        }
        catch(Exception ex){
            log.error("Export interrupted due to error: [{}]", ex.getMessage());
            ex.printStackTrace();
            res = -1;
        }
        return res;
    }

    private static void createFile(File path) throws IOException{
        boolean created = path.createNewFile();
        if(!created)
            log.info("Export file already exists - overwriting...");
    }

    private static void createFolder(File path) throws IOException{
        boolean dirExists = path.exists();
        if(dirExists){
            log.info("Output dir already exists - replacing...");

            Files.walk(path.toPath()) //delete the whole file tree of the dir with all contents
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        }

        path.mkdir();
    }

    private static boolean helpOptionPresent(String[] args){
        String single = "-" + H_HELP;
        String doubled = "--" + H_HELP_LONG;

        boolean present = Arrays.stream(args).anyMatch(arg -> single.equals(arg) || doubled.equals(arg));

        return present;
    }

    private static void printHelp(Options opt){
        HelpFormatter f = new HelpFormatter();
        f.printHelp(
                "java -jar meta2file.jar [options]",
                "[options]:",
                opt,
                null);
    }

    private static void mainWithArgs(String[] args, Options opt) throws Exception {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(opt, args);

        if(cmd.hasOption(D_DATA_DOWNLOAD_APP)){
            File destFile = new File(cmd.getOptionValue(D_DATA_DOWNLOAD_APP));

            try(Connection con = createCon(cmd)){
                exportAppFile(con, destFile);
            }

            log.info("Finished exporting to file {}.", destFile);
        }
        else if (cmd.hasOption(G_GEOCAT_FOLDER)){
            File destFolder = new File(cmd.getOptionValue(G_GEOCAT_FOLDER));

            try(Connection con = createCon(cmd)){
                exportGeocatFiles(con, destFolder);
            }

            log.info("Finished exporting into folder {}.", destFolder);
        }
        else {
            System.out.println("Please define option -d or -g for the export destination");
        }
    }

    private static Connection createCon(CommandLine cmd) throws SQLException {
        String conUrl = cmd.getOptionValue(C_CONNECTION);
        String user = cmd.getOptionValue(U_USER);
        String pass = cmd.getOptionValue(P_PASSWORD);

        log.info("Connecting to url {} with user {}", conUrl, user, pass.replaceAll(".*", "*"));

        return DriverManager.getConnection(conUrl, user, pass);
    }

    private static Options initOptions(){   
        Options os = new Options();

        os.addRequiredOption(C_CONNECTION.toString(), "conn", true, "Connection string as jdbc url");
        os.addRequiredOption(U_USER.toString(), "user", true, "database user for connect");
        os.addRequiredOption(P_PASSWORD.toString(), "pass", true, "database password for connect");
        os.addRequiredOption(E_ENV.toString(), "env", true, "Environment: String starting with p,i,t for prod,int,test");
        os.addOption(D_DATA_DOWNLOAD_APP.toString(), "dataFile", true, "File path destination for the resulting xml conf file");
        os.addOption(G_GEOCAT_FOLDER.toString(), "geocatFolder", true, "Destination folder for all iso-ch geocat xml files");
        os.addOption(H_HELP.toString(), H_HELP_LONG, false, "Print out this help message");

        return os;
    }

    /**
     * Exports the xml "config" file for the data search application.
     * @param con Connection to meta db.
     * @param xmlFile Destination path for the xml file.
     */
    static void exportAppFile(Connection con, File xmlFile) throws IOException {
        if(!xmlFile.exists()){
            xmlFile.createNewFile();

            if(!xmlFile.isFile())
                throw new Meta2FileException("\"{0}\" is no file path", xmlFile);
        }

        TpIterator iter = new TpIterator(con, environment);
        MetaBean2FileConverter.runBeans2Xml(xmlFile.toPath(), iter);
    }

    /**
     * Exports the iso-ch xml files for geocat.
     * Geocat needs one xml per ThemePublication.
     * @param con Connection to meta db.
     * @param xmlFolder Filesys folder to export the xml files into.
     */
    static void exportGeocatFiles(Connection con, File xmlFolder) throws IOException {
        if(!xmlFolder.isDirectory())
          throw new Meta2FileException("\"{0}\" is no folder path", xmlFolder);

        TpIterator iter = new TpIterator(con, environment);
        Geocat.beans2Files(xmlFolder.toPath(), iter);
    }
}
