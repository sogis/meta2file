package ch.so.agi.metabean2file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import ch.so.agi.metabean2file.model.Dataset;

class MetaBean2FileConverterTest {
    
    @Test
    public void bean2html_Ok() throws Exception {
        String datasetName = "ch.so.agi.av_gb_administrative_einteilung";
        
        HashMap<String,Dataset> datasets = TestUtils.getDatasets();
        Dataset dataset = datasets.get(datasetName);

        File htmlFile = MetaBean2FileConverter.runBean2Html(dataset);
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFile.getAbsolutePath())));
        
        File controlFile = new File("src/test/data-expected/"+datasetName+".html");
        String controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, htmlContent);
    }
}
