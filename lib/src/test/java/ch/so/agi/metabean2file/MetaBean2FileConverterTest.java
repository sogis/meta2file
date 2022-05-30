package ch.so.agi.metabean2file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ch.so.agi.metabean2file.model.ThemePublication;

class MetaBean2FileConverterTest {
    @Test
    public void createStacFiles_Ok() throws Exception {
        System.out.println("Hallo Welt.");
        
        String themePublicationName = "ch.so.agi.av_gb_administrative_einteilung";
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        ThemePublication themePublication = themePublications.get(themePublicationName);

        //File tmpFolder = Files.createTempDirectory("metabean2filetest-").toFile();
        File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        Path collectionFilePath = Paths.get(tmpFolder.getAbsolutePath(), themePublicationName+"_collection.json");

        MetaBean2FileConverter converter = new MetaBean2FileConverter();
        converter.createStacFiles(collectionFilePath, themePublication);
        
        
        System.out.println("Hallo Stefan.");
    }


    @Test
    public void beans2xml_Ok() throws Exception {
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        List<ThemePublication> list = new ArrayList<ThemePublication>(themePublications.values());

        Iterator<ThemePublication> it = list.iterator();
        
        File tmpFolder = Files.createTempDirectory("metabean2filetest-").toFile();
        //File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        Path xmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), "themepublications.xml");

        MetaBean2FileConverter.runBeans2Xml(xmlFilePath, it);
        String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath.toFile().getAbsolutePath())));
        
        File controlFile = new File("src/test/data-expected/themepublications.xml");
        String controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, xmlContent);
    }
    
    @Test
    public void bean2html_Ok() throws Exception {
        String themePublicationName = "ch.so.agi.av_gb_administrative_einteilung";
        
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        ThemePublication themePublication = themePublications.get(themePublicationName);

        File tmpFolder = Files.createTempDirectory("metabean2filetest-").toFile();
        //File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        Path htmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), themePublication.getIdentifier()+".html");

        MetaBean2FileConverter.runBean2Html(htmlFilePath, themePublication);
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath.toFile().getAbsolutePath())));
        
        File controlFile = new File("src/test/data-expected/"+themePublicationName+".html");
        String controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, htmlContent);
    }
}
