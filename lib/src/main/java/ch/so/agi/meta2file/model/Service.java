package ch.so.agi.meta2file.model;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.libmain.BaseUrl;
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
        URI res = null;

        if(type == ServiceType.DATA)
            res = BaseUrl.DATA_SERVICE.getBaseUrlAsUri();
        else if(type == ServiceType.WFS)
            res = BaseUrl.WFS.getBaseUrlAsUri();
        else if(type == ServiceType.WGC)
            res = BaseUrl.WGC.getBaseUrlAsUri();
        else if(type == ServiceType.WMS)
            res = BaseUrl.WMS.getBaseUrlAsUri();
        else
            throw new Meta2FileException("No base url defined for service type {0}", type);

        return res;
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
