package ch.so.agi.metabean2file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ch.so.agi.metabean2file.model.ThemePublication;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;

public class MetaBean2FileConverter {
    static Logger log = LoggerFactory.getLogger(MetaBean2FileConverter.class);

    private static final String XSL2HTML_FILE = "xml2html.xsl"; 
    private static XmlMapper xmlMapper = null;
    
    private static final String PYTHON = "python";
    private static final String VENV_EXECUTABLE = MetaBean2FileConverter.class.getClassLoader().getResource(Paths.get("venv", "bin", "graalpython").toString()).getPath();
    private static final String SOURCE_FILE_NAME = "staccreator.py";

    public void createStacFiles(Path collectionFilePath, ThemePublication themePublication) throws IOException {
        Context context = Context.newBuilder(PYTHON).
            allowAllAccess(true).
            option("python.Executable", VENV_EXECUTABLE).
            option("python.ForceImportSite", "true").
            build();
        InputStreamReader code = new InputStreamReader(MetaBean2FileConverter.class.getClassLoader().getResourceAsStream(SOURCE_FILE_NAME));
                
        Source source;
        try {
            source = Source.newBuilder(PYTHON, code, SOURCE_FILE_NAME).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        context.eval(source);
        Value pystacCreatorClass = context.getPolyglotBindings().getMember("StacCreator");
        Value pystacCreator = pystacCreatorClass.newInstance();
        
        StacCreator stacCreator = pystacCreator.as(StacCreator.class);
        stacCreator.create(collectionFilePath.toFile().getAbsolutePath(), themePublication);

        
        context.close(); // static?
    }

    /**
     * Converts a collection of theme publications to a xml file.
     * 
     * @param xmlFilePath
     * @param datasetsIterator
     * @throws MetaBean2FileException
     */
    public static void runBeans2Xml(Path xmlFilePath, Iterator<ThemePublication> datasetsIterator) throws MetaBean2FileException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, false);
        xmlMapper.disable(SerializationFeature.INDENT_OUTPUT);

        var xof = XMLOutputFactory.newFactory();
        try {
            var xsw = xof.createXMLStreamWriter(new FileWriter(xmlFilePath.toFile().getAbsolutePath()));
            xsw.writeStartDocument("utf-8", "1.0");
            xsw.writeStartElement("themePublications");
            while(datasetsIterator.hasNext()) {
                var themePub = datasetsIterator.next();
                xmlMapper.writeValue(xsw, themePub);
            }
            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
            xsw.close();
            
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new MetaBean2FileException(e.getMessage());
        }
    }
    
    /**
     * Converts a single theme publication to a html file.
     * 
     * @param htmlFilePath
     * @param dataset
     * @throws MetaBean2FileException
     */
    public static void runBean2Html(Path htmlFilePath, ThemePublication dataset) throws MetaBean2FileException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        
        String xmlResult;
        try {
            xmlResult = xmlMapper.writeValueAsString(dataset);
            
            var tmpFolder = Files.createTempDirectory("metabean2file-").toFile();
            //File tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
            var xmlFile = Paths.get(tmpFolder.getAbsolutePath(), dataset.getIdentifier()+".xml").toFile();
            xmlMapper.writeValue(xmlFile, dataset);

            var xslFile = Paths.get(tmpFolder.getAbsolutePath(), XSL2HTML_FILE).toFile();
            Util.loadFile(XSL2HTML_FILE, xslFile);
            
            var htmlFile = htmlFilePath.toFile();
            //log.info(htmlFile.getAbsolutePath());

            var processor = new Processor(false);
            var compiler = processor.newXsltCompiler();
            var stylesheet = compiler.compile(new StreamSource(xslFile));
            var out = processor.newSerializer(htmlFile);
            //out.setOutputProperty(Serializer.Property.METHOD, "html");
            //out.setOutputProperty(Serializer.Property.INDENT, "yes");
            var transformer = stylesheet.load30();
            transformer.transform(new StreamSource(xmlFile), out);
            
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
