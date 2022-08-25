package ch.so.agi.meta2file.out.geocat;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.model.ThemePublication;
import ch.so.agi.meta2file.model.geocat.ThemePublicationGC;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

public class Geocat {

    private static Configuration cfg;

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_31);

        Path tplDir = Path.of("src/main/resources/freemarker").toAbsolutePath();
        try {
            cfg.setDirectoryForTemplateLoading(tplDir.toFile());
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
            Template tpl = cfg.getTemplate("geocat_template.xml");

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

}

