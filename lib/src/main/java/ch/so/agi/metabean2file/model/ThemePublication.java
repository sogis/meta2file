package ch.so.agi.metabean2file.model;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *  Themapubliaktion entspricht einer Zeile in der Datensuche 
 */
@JacksonXmlRootElement(localName = "themePublication")
@JsonInclude(Include.NON_NULL)
public class ThemePublication {
    /**
     *  Eindeutiger Identifier, z.B. ch.so.arp.waldreservate
     */
    @NotNull
    private String identifier; 
    /** 
     * INTERLIS-Datenmodell
     */
    @NotNull
    private String model;
    @NotNull
    private LocalDate lastPublishingDate;
    @NotNull
    private LocalDate secondToLastPublishingDate = LocalDate.of(1848, 9, 12);
    @NotNull
    private String title;
    @JacksonXmlCData
    @NotNull
    private String shortDescription;
    private String keywords;
    private String synonyms;
    @NotNull
    private Office owner;
    @NotNull
    private Office servicer; 
    private URI furtherInformation;
    @NotNull
    private URI licence;
    @NotNull
    private URI baseUrl;
    @JacksonXmlElementWrapper(localName = "fileFormats")
    @JacksonXmlProperty(localName = "fileFormat")
    @NotNull
    private List<FileFormat> fileFormats; 
    @NotNull
    @JacksonXmlElementWrapper(localName = "items")
    @JacksonXmlProperty(localName = "item")
    private List<Item> items;
    @JacksonXmlElementWrapper(localName = "tablesInfo")
    @JacksonXmlProperty(localName = "tableInfo")
    private List<TableInfo> tablesInfo; 
    @JacksonXmlElementWrapper(localName = "services")
    @JacksonXmlProperty(localName = "service")
    private List<Service> services;
    private BoundingBox bbox;
    
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public LocalDate getLastPublishingDate() {
        return lastPublishingDate;
    }
    public void setLastPublishingDate(LocalDate lastPublishingDate) {
        this.lastPublishingDate = lastPublishingDate;
    }
    public LocalDate getSecondToLastPublishingDate() {
        return secondToLastPublishingDate;
    }
    public void setSecondToLastPublishingDate(LocalDate secondToLastPublishingDate) {
        this.secondToLastPublishingDate = secondToLastPublishingDate;
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
    public String getSynonyms() {
        return synonyms;
    }
    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
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
    public URI getLicence() {
        return licence;
    }
    public void setLicence(URI licence) {
        this.licence = licence;
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
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
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
    public BoundingBox getBbox() {
        return bbox;
    }
    public void setBbox(BoundingBox bbox) {
        this.bbox = bbox;
    }
}
