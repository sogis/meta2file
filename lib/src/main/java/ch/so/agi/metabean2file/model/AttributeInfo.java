package ch.so.agi.metabean2file.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AttributeInfo {
    @NotNull
    private String name;
    private String shortDescription;
    @NotNull
    private String datatype;
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
}
