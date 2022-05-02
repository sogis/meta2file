package ch.so.agi.metabean2file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ch.so.agi.metabean2file.model.ThemePublication;

class MetaBean2FileConverterTest {
    
    @Test
    public void beans2xml_Ok() throws Exception {
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        Set entrySet = themePublications.entrySet();
        Iterator it = entrySet.iterator();

        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            System.out.println("Key is: "+me.getKey() + 
            " & " + 
            " value is: "+me.getValue());
        }
    }
    
    
    @Test
    public void bean2html_Ok() throws Exception {
        String themePublicationName = "ch.so.agi.av_gb_administrative_einteilung";
        
        HashMap<String,ThemePublication> themePublications = TestUtils.getDatasets();
        ThemePublication themePublication = themePublications.get(themePublicationName);

        File tmpFolder = Files.createTempDirectory("metabean2file-").toFile();
        //File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        Path htmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), themePublication.getIdentifier()+".html");

        MetaBean2FileConverter.runBean2Html(htmlFilePath, themePublication);
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath.toFile().getAbsolutePath())));
        
        File controlFile = new File("src/test/data-expected/"+themePublicationName+".html");
        String controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, htmlContent);
    }
}
