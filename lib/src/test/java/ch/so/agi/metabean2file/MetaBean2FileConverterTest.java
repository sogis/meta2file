package ch.so.agi.metabean2file;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import ch.so.agi.metabean2file.model.Dataset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MetaBean2FileConverterTest {
    /*
    @Test 
    public void someLibraryMethodReturnsTrue() {
        MetaBean2FileConverter classUnderTest = new MetaBean2FileConverter();
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
    */
    
    @Test
    public void bean2pdf_Ok() throws Exception {
        //MetaBean2FileConverter converter = new MetaBean2FileConverter();
        
        List<Dataset> datasets = Utils.getDatasets();
        Dataset dataset = datasets.get(0);

        
        MetaBean2FileConverter.runBean2Html(dataset);
    }
}
