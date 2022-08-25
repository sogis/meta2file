package ch.so.agi.meta2file.model.geocat;

import ch.so.agi.meta2file.libmain.BaseUrl;
import ch.so.agi.meta2file.model.FileFormat;
import ch.so.agi.meta2file.model.ThemePublication;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;

public class FileFormatGC {

    private ThemePublication parent;
    private FileFormat inner;
    private static HashMap<String, String> protocols;
    private UUID id;

    static{
        protocols = new HashMap<>();
        protocols.put("gpkg", "WWW:DOWNLOAD:Geopackage (ogc)");
        protocols.put("xtf", "WWW:DOWNLOAD:INTERLIS");
        protocols.put("itf", "WWW:DOWNLOAD:INTERLIS");
    }

    public FileFormatGC(FileFormat inner, ThemePublication parent){
        this.inner = inner;
        this.parent = parent;
        this.id = UUID.randomUUID();
    }

    public String getUrl(){
        String[] nameParts = new String[]{
                parent.getIdentifier(),
                inner.getAbbreviation(),
                "zip"
        };

        String name = String.join(".", nameParts);

        URI full = BaseUrl.FILES.getBaseUrlAsUri().resolve(name);
        return full.toString();
    }

    public String getName(){
        return inner.getName() + " (in Zip)";
    }

    public String getVersion(){
        if(inner.getAbbreviation() == null)
            return null;

        String res = "-";

        if(inner.getAbbreviation().equals("xtf"))
            res = "2";
        else if(inner.getAbbreviation().equals("itf"))
            res = "1";

        return res;
    }

    public String getIdString(){
        return id.toString();
    }

    public String getProtocol(){
        return protocols.getOrDefault(inner.getAbbreviation(), "WWW:DOWNLOAD-URL");
    }
}
