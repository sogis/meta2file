package ch.so.agi.metabean2file.model;

import java.net.URI;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Office {
    @NotNull
    private String agencyName;
    @NotNull
    private String abbrevation;
    private String division;
    @NotNull
    private URI officeAtWeb;
    @NotNull
    private URI email;
    private String phone;
    
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getAbbrevation() {
        return abbrevation;
    }
    public void setAbbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
    }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public URI getOfficeAtWeb() {
        return officeAtWeb;
    }
    public void setOfficeAtWeb(URI officeAtWeb) {
        this.officeAtWeb = officeAtWeb;
    }
    public URI getEmail() {
        return email;
    }
    public void setEmail(URI email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
