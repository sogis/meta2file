package ch.so.agi.meta2file.out.geocat;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.model.ThemePublication;
import ch.so.agi.meta2file.model.geocat.ThemePublicationGC;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import net.sf.saxon.om.CopyOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

public class Geocat {

    private static final String TEMPLATE_FILENAME = "geocat_template.xml";
    private static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_31);

        Path templatePath = copyTemplateToTmpDir();
        try {
            cfg.setDirectoryForTemplateLoading(templatePath.getParent().toFile());
        } catch (IOException e) {
            throw new Meta2FileException(e);
        }

        cfg.setDefaultEncoding("UTF-8");

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        cfg.setLogTemplateExceptions(false);

        cfg.setWrapUncheckedExceptions(true);

        cfg.setFallbackOnNullLoopVariable(false);
    }

    public static void beans2Files(Path baseDirPath, Iterator<ThemePublication> themePublicationsIterator) {
        try {
            Template tpl = cfg.getTemplate(TEMPLATE_FILENAME);

            while(themePublicationsIterator.hasNext()){
                ThemePublication tp = themePublicationsIterator.next();
                Path file = baseDirPath.resolve(tp.getIdentifier() + ".xml");

                try(FileWriter writer = new FileWriter(file.toFile())){
                    ThemePublicationGC tpCat = new ThemePublicationGC(tp);
                    tpl.process(tpCat, writer);
                }
            }
        } catch (Exception e) {
            throw new Meta2FileException(e);
        }
    }

    private static Path copyTemplateToTmpDir(){

        Path exportedTemplate = null;
        try {
            InputStream is = Geocat.class.getClassLoader().getResourceAsStream("freemarker/" + TEMPLATE_FILENAME);
            Path exportDir = Files.createTempDirectory("freemarker");
            exportedTemplate = exportDir.resolve(TEMPLATE_FILENAME);
            Files.copy(is, exportedTemplate, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new Meta2FileException(e);
        }
        return exportedTemplate;
    }

}

