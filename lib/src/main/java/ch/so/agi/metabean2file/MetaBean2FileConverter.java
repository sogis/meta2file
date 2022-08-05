package ch.so.agi.metabean2file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import ch.so.agi.metabean2file.model.ThemePublication;

public class MetaBean2FileConverter {
    static Logger log = LoggerFactory.getLogger(MetaBean2FileConverter.class);

    private static final String XSL2HTML_FILE = "xml2html.xsl"; 
    
    private static XmlMapper xmlMapper = null;
    
    /**
     * Converts a collection of theme publications to a xml file.
     * 
     * @param xmlFilePath
     * @param themePublicationsIterator
     * @throws MetaBean2FileException
     */
    public static void runBeans2Xml(Path xmlFilePath, Iterator<ThemePublication> themePublicationsIterator) throws MetaBean2FileException {
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
            while(themePublicationsIterator.hasNext()) {
                var themePub = themePublicationsIterator.next();
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
     * @param themePublications
     * @throws MetaBean2FileException
     */
    public static void runBean2Html(Path htmlFilePath, ThemePublication themePublications) throws MetaBean2FileException {
        if (xmlMapper == null) {
            MetaBean2FileConverter.initMapper();
        }
        
        String xmlResult;
        try {
            xmlResult = xmlMapper.writeValueAsString(themePublications);
            
            var tmpFolder = Files.createTempDirectory("metabean2file-").toFile();
            //var tmpFolder = new File("/Users/stefan/tmp/metabean2file/");
            var xmlFile = Paths.get(tmpFolder.getAbsolutePath(), themePublications.getIdentifier()+".xml").toFile();
            xmlMapper.writeValue(xmlFile, themePublications);

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
    }
    
    private static void initMapper() {
        xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new JavaTimeModule());
    }
}
