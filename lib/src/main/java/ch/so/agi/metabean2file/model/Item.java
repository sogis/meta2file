package ch.so.agi.metabean2file.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class Item {
    @NotNull
    private String identifier;
    @NotNull
    private String title;
    @NotNull
    private LocalDate lastPublishingDate;
    // Darf Null sein, wird momentan bei Bedarf aus Geometrie abgeleitet.
    private BoundingBox bbox;
    // WKT-String, EPSG:2056
    @NotNull
    private String geometry;
    
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDate getLastPublishingDate() {
        return lastPublishingDate;
    }
    public void setLastPublishingDate(LocalDate lastPublishingDate) {
        this.lastPublishingDate = lastPublishingDate;
    }
    public BoundingBox getBbox() {
        return bbox;
    }
    public void setBbox(BoundingBox bbox) {
        this.bbox = bbox;
    }
    public String getGeometry() {
        return geometry;
    }
    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }
}
