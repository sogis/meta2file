package ch.so.agi.metabean2file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SAXDestination;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

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
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import ch.so.agi.metabean2file.model.Dataset;
import net.sf.saxon.s9api.Xslt30Transformer;

public class MetaBean2FileConverter {
    static Logger log = LoggerFactory.getLogger(MetaBean2FileConverter.class);

    private static final String XSL2HTML_FILE = "xml2html.xsl"; 
    
    private static XmlMapper xmlMapper = null;

    public static File runBean2Html(Dataset dataset) throws MetaBean2FileException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        
        String xmlResult;
        try {
            xmlResult = xmlMapper.writeValueAsString(dataset);
            System.out.println(xmlResult);
            
            //File tmpFolder = Files.createTempDirectory("metabean2file-").toFile();
            File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
            File xmlFile = Paths.get(tmpFolder.getAbsolutePath(), dataset.getName()+".xml").toFile();
            log.info(xmlFile.getAbsolutePath());
            xmlMapper.writeValue(xmlFile, dataset);

            File xslFile = Paths.get(tmpFolder.getAbsolutePath(), XSL2HTML_FILE).toFile();
            Util.loadFile(XSL2HTML_FILE, xslFile);
            
            File htmlFile = Paths.get(tmpFolder.getAbsolutePath(), dataset.getName()+".html").toFile();
            log.info(htmlFile.getAbsolutePath());

            Processor processor = new Processor(false);
            XsltCompiler compiler = processor.newXsltCompiler();
            XsltExecutable stylesheet = compiler.compile(new StreamSource(xslFile));
            Serializer out = processor.newSerializer(htmlFile);
            //out.setOutputProperty(Serializer.Property.METHOD, "html");
            //out.setOutputProperty(Serializer.Property.INDENT, "yes");
            Xslt30Transformer transformer = stylesheet.load30();
            transformer.transform(new StreamSource(xmlFile), out);
            
            return htmlFile;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new MetaBean2FileException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MetaBean2FileException(e.getMessage());
        } catch (SaxonApiException e) {
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
    }
    
    private static void initMapper() {
        xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new JavaTimeModule());
    }
}
