package ch.so.agi.meta2file.appmain;


import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.db.TpIterator;
import ch.so.agi.meta2file.out.MetaBean2FileConverter;
import ch.so.agi.meta2file.out.geocat.Geocat;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Meta2File {
    private static final Character C_CONNECTION = 'c';
    private static final Character U_USER = 'u';
    private static final Character P_PASSWORD = 'p';
    private static final Character D_DATA_DOWNLOAD_APP = 'd';
    private static final Character G_GEOCAT_FOLDER = 'g';
    private static final Character H_HELP = 'h';

    private static Logger log = LoggerFactory.getLogger(Meta2File.class);

    public static void main(String[] args) throws Exception {

        try{
            Options opt = initOptions();

            if(args == null || args.length == 0){
                printHelp(opt);
                return;
            }

            mainWithArgs(args, opt);
        }
        catch(MissingArgumentException mex){
            System.out.println(mex.getMessage());
        }
        catch(Exception ex){
            log.error("Encountered error. exiting...\n\n");

            throw ex;
        }
    }

    private static void printHelp(Options opt){
        HelpFormatter f = new HelpFormatter();
        f.printHelp(
                "java -jar meta2file.jar [options]",
                "[options]:",
                opt,
                "Version: " + Meta2File.class.getPackage().getImplementationVersion());
    }

    private static void mainWithArgs(String[] args, Options opt) throws ParseException, SQLException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(opt, args);

        if(cmd.hasOption(H_HELP)){
            printHelp(opt);
            return;
        }

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

        os.addRequiredOption(C_CONNECTION.toString(), "connection", true, "Connection string as jdbc url (with schema)");
        os.addRequiredOption(U_USER.toString(), "user", true, "database user for connect");
        os.addRequiredOption(P_PASSWORD.toString(), "pass", true, "database password for connect");
        os.addOption(D_DATA_DOWNLOAD_APP.toString(), "dataAppConf", true, "File path destination for the resulting xml conf file");
        os.addOption(G_GEOCAT_FOLDER.toString(), "geocatExport", true, "Destination folder for all iso-ch geocat xml files");
        os.addOption(H_HELP.toString(), "help", false, "Print out this help message");

        return os;
    }

    /**
     * Exports the xml "config" file for the data search application.
     * @param con Connection to meta db.
     * @param xmlFile Destination path for the xml file.
     */
    static void exportAppFile(Connection con, File xmlFile){
        if(!xmlFile.isFile())
            throw new Meta2FileException("\"{0}\" is no file path", xmlFile);

        TpIterator iter = new TpIterator(con);
        MetaBean2FileConverter.runBeans2Xml(xmlFile.toPath(), iter);
    }

    /**
     * Exports the iso-ch xml files for geocat.
     * Geocat needs one xml per ThemePublication.
     * @param con Connection to meta db.
     * @param xmlFolder Filesys folder to export the xml files into.
     */
    static void exportGeocatFiles(Connection con, File xmlFolder){
        if(!xmlFolder.isDirectory())
            throw new Meta2FileException("\"{0}\" is no folder path", xmlFolder);

        TpIterator iter = new TpIterator(con);
        Geocat.beans2Files(xmlFolder.toPath(), iter);
    }
}

/*
Fragen
- model.TableInfo: Attribut-Liste absichtlich optional?
- SHEET: Geometrie-Spalte wird dann einfach irgendwo in den Attributen einsortiert. Auch das ist bewusst so gewollt?
 */
