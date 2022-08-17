package ch.so.agi.meta2file.model;

import java.net.URI;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Informationen zu einem Service, in welchem Daten
 * publiziert sind.
 */
public class Service {
    /**
     * Endpunkt, z.B. WMS-GetCapabilities
     */
    @NotNull
    private URI endpoint;
    /**
     * Layernamen in welchen Daten dieses Themas publiziert sind.
     */
    @JacksonXmlElementWrapper(localName = "layerNames")
    @JacksonXmlProperty(localName = "layerName")
    @NotNull
    private List<String> layerIdentifiers;
    /**
     * Service-Typ
     */
    @NotNull
    private ServiceType type;
    public URI getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }
    public List<String> getLayerIdentifiers() {
        return layerIdentifiers;
    }
    public void setLayerIdentifiers(List<String> layerIdentifiers) {
        this.layerIdentifiers = layerIdentifiers;
    }
    public ServiceType getType() {
        return type;
    }
    public void setType(ServiceType type) {
        this.type = type;
    }
}
