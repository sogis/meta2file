package ch.so.agi.metabean2file.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FileFormat {
    /**
     * Sprechender ("schöner") Name
     */
    @NotNull
    private String name;
    /**
     * Technischer Name des Formats.
     */
    @NotNull
    private String mimetype;
    /**
     * Abkürzung des Formats.
     */
    @NotNull
    private String abbrevation;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMimetype() {
        return mimetype;
    }
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
    public String getAbbrevation() {
        return abbrevation;
    }
    public void setAbbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
    }
}
