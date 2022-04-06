package ch.so.agi.metabean2file.model;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class Dataset {
    private String id; // uuid
    private String model;
    private Date lastEditingDate; 
    private String title;
    private String shortDescription;
    private String keywords;
    private String owner; // Datenherr (Office)
    private String servicer; // "AGI" (Office)
    private URI furtherInformation; // Weitere Infos
    private URI furtherMetadata; // Link auf Metadaten-Whatever, z.B. geocat
    private List<String> fileFormats; // (FileFormat) List
    private String subunits; // base64
    private List<String> tablesInfo; //(TableInfo) List
    private List<String> serviceLayers; //(ServiceLayerr) List
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Date getLastEditingDate() {
        return lastEditingDate;
    }
    public void setLastEditingDate(Date lastEditingDate) {
        this.lastEditingDate = lastEditingDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getServicer() {
        return servicer;
    }
    public void setServicer(String servicer) {
        this.servicer = servicer;
    }
    public URI getFurtherInformation() {
        return furtherInformation;
    }
    public void setFurtherInformation(URI furtherInformation) {
        this.furtherInformation = furtherInformation;
    }
    public URI getFurtherMetadata() {
        return furtherMetadata;
    }
    public void setFurtherMetadata(URI furtherMetadata) {
        this.furtherMetadata = furtherMetadata;
    }
    public List<String> getFileFormats() {
        return fileFormats;
    }
    public void setFileFormats(List<String> fileFormats) {
        this.fileFormats = fileFormats;
    }
    public String getSubunits() {
        return subunits;
    }
    public void setSubunits(String subunits) {
        this.subunits = subunits;
    }
    public List<String> getTablesInfo() {
        return tablesInfo;
    }
    public void setTablesInfo(List<String> tablesInfo) {
        this.tablesInfo = tablesInfo;
    }
    public List<String> getServiceLayers() {
        return serviceLayers;
    }
    public void setServiceLayers(List<String> serviceLayers) {
        this.serviceLayers = serviceLayers;
    }

    
}
