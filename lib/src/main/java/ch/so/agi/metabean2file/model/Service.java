package ch.so.agi.metabean2file.model;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Service {
    private URI endpoint;
    @JacksonXmlElementWrapper(localName = "layerNames")
    @JacksonXmlProperty(localName = "layerName")
    private List<String> layerNames;
    private String type;
    
    public URI getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }
    public List<String> getLayerNames() {
        return layerNames;
    }
    public void setLayerNames(List<String> layerNames) {
        this.layerNames = layerNames;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
