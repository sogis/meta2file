package ch.so.agi.metabean2file.model;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "dataset")
@JsonInclude(Include.NON_NULL)
public class Dataset {
    private String id; // uuid
    private String name; 
    private String model;
    private LocalDate lastEditingDate; 
    private String title;
    @JacksonXmlCData
    private String shortDescription;
    private String keywords;
    private Office owner; 
    private Office servicer; 
    private URI furtherInformation; 
    private URI furtherMetadata;
    private URI baseUrl; // Falls Spezialfall und Datei irgendwo anders liegt (??)
    @JacksonXmlElementWrapper(localName = "fileFormats")
    //@JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "fileFormat")
    private List<FileFormat> fileFormats; 
    private String subunits; // base64
    @JacksonXmlElementWrapper(localName = "tablesInfo")
    @JacksonXmlProperty(localName = "tableInfo")
    private List<TableInfo> tablesInfo; 
    @JacksonXmlElementWrapper(localName = "services")
    @JacksonXmlProperty(localName = "service")
    private List<Service> services; 
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public LocalDate getLastEditingDate() {
        return lastEditingDate;
    }
    public void setLastEditingDate(LocalDate lastEditingDate) {
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
    public Office getOwner() {
        return owner;
    }
    public void setOwner(Office owner) {
        this.owner = owner;
    }
    public Office getServicer() {
        return servicer;
    }
    public void setServicer(Office servicer) {
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
    public URI getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(URI baseUrl) {
        this.baseUrl = baseUrl;
    }
    public List<FileFormat> getFileFormats() {
        return fileFormats;
    }
    public void setFileFormats(List<FileFormat> fileFormats) {
        this.fileFormats = fileFormats;
    }
    public String getSubunits() {
        return subunits;
    }
    public void setSubunits(String subunits) {
        this.subunits = subunits;
    }
    public List<TableInfo> getTablesInfo() {
        return tablesInfo;
    }
    public void setTablesInfo(List<TableInfo> tablesInfo) {
        this.tablesInfo = tablesInfo;
    }
    public List<Service> getServices() {
        return services;
    }
    public void setServices(List<Service> services) {
        this.services = services;
    }    
}
