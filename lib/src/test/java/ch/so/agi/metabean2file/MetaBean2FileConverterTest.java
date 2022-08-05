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
    public void beans2xml_Ok() throws Exception {
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        var list = new ArrayList<ThemePublication>(themePublications.values());

        var it = list.iterator();
        
        var tmpFolder = Files.createTempDirectory("metabean2filetest-").toFile();
        //var tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        var xmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), "themepublications.xml");

        MetaBean2FileConverter.runBeans2Xml(xmlFilePath, it);
        var xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath.toFile().getAbsolutePath())));
        
        var controlFile = new File("src/test/data-expected/themepublications.xml");
        var controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, xmlContent);
    }
    
    @Test
    public void bean2html_Ok() throws Exception {
        var themePublicationName = "ch.so.agi.av_gb_administrative_einteilung";
        
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        var themePublication = themePublications.get(themePublicationName);

        var tmpFolder = Files.createTempDirectory("metabean2filetest-").toFile();
        //var tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        var htmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), themePublication.getIdentifier()+".html");

        MetaBean2FileConverter.runBean2Html(htmlFilePath, themePublication);
        var htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath.toFile().getAbsolutePath())));
        
        var controlFile = new File("src/test/data-expected/"+themePublicationName+".html");
        var controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, htmlContent);
    }
}
