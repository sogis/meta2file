package ch.so.agi.metabean2file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import ch.so.agi.metabean2file.model.ThemePublication;

class MetaBean2FileConverterTest {
    @Test
    public void bean2html_Ok() throws Exception {
        String datasetName = "ch.so.agi.av_gb_administrative_einteilung";
        
        HashMap<String,ThemePublication> datasets = TestUtils.getDatasets();
        ThemePublication dataset = datasets.get(datasetName);

        File tmpFolder = Files.createTempDirectory("metabean2file-").toFile();
        //File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
        Path htmlFilePath = Paths.get(tmpFolder.getAbsolutePath(), dataset.getIdentifier()+".html");

        MetaBean2FileConverter.runBean2Html(htmlFilePath, dataset);
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath.toFile().getAbsolutePath())));
        
        File controlFile = new File("src/test/data-expected/"+datasetName+".html");
        String controlContent = new String(Files.readAllBytes(Paths.get(controlFile.getAbsolutePath())));
      
        assertEquals(controlContent, htmlContent);
    }
}
