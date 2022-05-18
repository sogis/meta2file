package ch.so.agi.metabean2file.model;

import java.time.LocalDate;

/* 
 * Entspricht plusminus dem Subunit-INTERLIS-Modell?
 * Was ist mandatory? Was wird implizit "geerbt"/"vererbt"?
 */
public class Item {
    private String identifier;
    private String title;
    private LocalDate lastPublishingDate;
    private BoundingBox bbox;
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
