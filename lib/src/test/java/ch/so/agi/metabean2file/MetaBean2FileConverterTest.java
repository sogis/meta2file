package ch.so.agi.metabean2file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

import ch.so.agi.metabean2file.model.Dataset;

class MetaBean2FileConverterTest {
    
    @Test
    public void bean2pdf_Ok() throws Exception {
        List<Dataset> datasets = TestUtils.getDatasets();
        Dataset dataset = datasets.get(0);

        
        MetaBean2FileConverter.runBean2Html(dataset);
    }
}
