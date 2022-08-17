package ch.so.agi.meta2file.out;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Holds the singleton for the Jackson XmlMapper used
 * in generating all output formats.
 */
public class TpXmlMapper {

    private static XmlMapper mapper;

    public static XmlMapper singleton(){
        if(mapper == null)
            mapper = createMapper();

        return mapper;
    }

    private static XmlMapper createMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new JavaTimeModule());

        return xmlMapper;
    }
}
