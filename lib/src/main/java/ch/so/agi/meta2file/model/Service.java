package ch.so.agi.meta2file.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

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
     * Publizierte Layer, die Daten dieses Themas enthalten.
     */
    @JacksonXmlElementWrapper(localName = "layers")
    @JacksonXmlProperty(localName = "layer")
    @NotNull
    private List<Layer> layers;
    
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
    
    public ServiceType getType() {
        return type;
    }
    
    public void setType(ServiceType type) {
        this.type = type;
    }
    
    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
