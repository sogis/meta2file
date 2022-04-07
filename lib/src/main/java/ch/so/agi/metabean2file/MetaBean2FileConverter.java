package ch.so.agi.metabean2file;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import ch.so.agi.metabean2file.model.Dataset;

public class MetaBean2FileConverter {
    Logger log = LoggerFactory.getLogger(MetaBean2FileConverter.class);

    private static XmlMapper xmlMapper = null;

    public static void runBean2Html(Dataset dataset) throws MetaBean2FileException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        
        String xmlResult;
        try {
            xmlResult = xmlMapper.writeValueAsString(dataset);
            System.out.println(xmlResult);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new MetaBean2FileException(e.getMessage());
        }
        
        /*
        YAMLFactory factory = new YAMLFactory();
        factory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        factory.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        factory.enable(YAMLGenerator.Feature.SPLIT_LINES);
        List<Dataset> foo = new ArrayList<>();
        foo.add(dataset);
        ObjectMapper om = new ObjectMapper(factory);
        om.enable(SerializationFeature.WRAP_ROOT_VALUE);
        System.out.println(om.writeValueAsString(foo));
        */
        
        //System.out.println(xmlMapper);
        //System.out.println("Hallo Welt.");
    }
    
    private static void initMapper() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new JavaTimeModule());
    }
}
