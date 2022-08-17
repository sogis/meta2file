package ch.so.agi.meta2file.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Informationen zu einzelnen Attributen einer Tabelle.
 */
@JsonInclude(Include.NON_NULL)
public class AttributeInfo {
    /**
     * Name des Attributes in der PostgreSQL-Datenbank
     */
    @NotNull
    private String name;

    /**
     * Sprechender Name des Attributes
     */
    private String alias;

    /**
     * Beschreibung des Attributes.
     */
    private String shortDescription;
    /**
     * Datentyp
     */
    @NotNull
    private String datatype;
    /**
     * Ist das Attribut zwingend?
     */
    @NotNull
    private boolean mandatory;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getDatatype() {
        return datatype;
    }
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    public boolean isMandatory() {
        return mandatory;
    }
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
