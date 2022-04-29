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
    private String line1;
    private String line2;
    private String street;
    private String number;
    private String postalCode;
    private String city;
    
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
    public String getLine1() {
        return line1;
    }
    public void setLine1(String line1) {
        this.line1 = line1;
    }
    public String getLine2() {
        return line2;
    }
    public void setLine2(String line2) {
        this.line2 = line2;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
