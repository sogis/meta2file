package ch.so.agi.metabean2file.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(Include.NON_NULL)
public class TableInfo {
    private String name; 
    private String description;
    @JacksonXmlElementWrapper(localName = "attributesInfo")
    @JacksonXmlProperty(localName = "attributeInfo")
    private List<AttributeInfo> attributesInfo;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<AttributeInfo> getAttributesInfo() {
        return attributesInfo;
    }
    public void setAttributesInfo(List<AttributeInfo> attributesInfo) {
        this.attributesInfo = attributesInfo;
    }
}
