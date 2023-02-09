package ch.so.agi.meta2file.model.geocat;

import ch.so.agi.meta2file.model.Office;
import net.sf.saxon.expr.instruct.Message;

import java.net.URI;
import java.text.MessageFormat;

public class OfficeGC {
    private Office inner;

    public OfficeGC(Office inner){
        this.inner = inner;
    }

    public String getName(){
        String res = inner.getAgencyName();

        if(inner.getDivision() != null && inner.getDivision().length() > 0)
            res = res + ", " + inner.getDivision();

        return res;
    }

    public String getPhone(){ return inner.getPhone(); }

    public String getEmail(){
        if(inner.getEmail() == null)
            return null;

        String mailAdress = inner.getEmail().getSchemeSpecificPart();
        return mailAdress;
    }

    public String getAbbreviation(){
        return inner.getAbbreviation();
    }

    public String getUrl(){
        if(inner.getOfficeAtWeb() == null)
            return null;

        return inner.getOfficeAtWeb().toString();
    }
}

/*
serviceOrg.name
serviceOrg.phone
serviceOrg.email
serviceOrg.abbreviation
serviceOrg.url
 */
