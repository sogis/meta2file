package ch.so.agi.meta2file.model;

import java.net.URI;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Informationen zu einem Amt
 */
@JsonInclude(Include.NON_NULL)
public class Office {
    /**
     * Name des Amts.
     */
    @NotNull
    private String agencyName;
    /**
     * Abkürzung, z.B. AfU.
     */
    @NotNull
    private String abbreviation;
    /**
     * Abteilung resp. Untereinheit des Amts.
     */
    private String division;
    /**
     * Webseite
     */
    @NotNull
    private URI officeAtWeb;
    /**
     * E-Mail des Amts oder der Unterheinheit (?)
     */
    @NotNull
    private URI email;
    /**
     * Telefonnummer des Amts oder der Unterheinheit (?)
     */
    private String phone;
    
    public String getAgencyName() {
        return agencyName;
    }
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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
