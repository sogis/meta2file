package ch.so.agi.metabean2file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ch.so.agi.metabean2file.model.Dataset;

public class MetaBean2FileConverter {
    Logger log = LoggerFactory.getLogger(MetaBean2FileConverter.class);

    private static XmlMapper xmlMapper = null;

    public static void runBean2Html(Dataset dataset) throws JsonProcessingException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        
        String xmlResult = xmlMapper.writeValueAsString(dataset);
        System.out.println(xmlResult);
        
        System.out.println(xmlMapper);
        System.out.println("Hallo Welt.");
    }
    
    private static void initMapper() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //xmlMapper.registerModule(new JavaTimeModule());
    }
}
