package ch.so.agi.meta2file.model.geocat;

import ch.so.agi.meta2file.appmain.Meta2File;
import ch.so.agi.meta2file.libmain.BaseUrl;
import ch.so.agi.meta2file.model.FileFormat;
import ch.so.agi.meta2file.model.ThemePublication;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class FileFormatGC {

    private static final String XTF_ABBREVIATION = "xtf.zip";
    private static final String ITF_ABBREVIATION = "itf.zip";
    private static final String GPKG_ABBREVIATION = "gpkg.zip";
    private static final String SHP_ABBREVIATION = "shp.zip";
    private static final String DXF_ABBREVIATION = "dxf.zip";
    private static final String GEOTIFF_ABBREVIATION = "tif";
    private static final String LAZ_ABBREVIATION = "laz";

    private ThemePublication parent;
    private FileFormat inner;
    private static HashMap<String, String> protocols;
    private UUID id;

    static{
        protocols = new HashMap<>();
        protocols.put(GPKG_ABBREVIATION, "WWW:DOWNLOAD:Geopackage (ogc)");
        protocols.put(XTF_ABBREVIATION, "WWW:DOWNLOAD:INTERLIS");
        protocols.put(ITF_ABBREVIATION, "WWW:DOWNLOAD:INTERLIS");
        protocols.put(SHP_ABBREVIATION, "WWW:DOWNLOAD:SHP");
        protocols.put(DXF_ABBREVIATION, "WWW:DOWNLOAD:DXF");
        protocols.put(GEOTIFF_ABBREVIATION, "WWW:DOWNLOAD:GeoTIFF");
        protocols.put(LAZ_ABBREVIATION, "WWW:DOWNLOAD:LAZ");
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
        };

        String name = String.join(".", nameParts);

        String url = BaseUrl.FILES.getBaseUrlAsUri(Meta2File.environment).toString() + "/" + parent.getIdentifier() + "/aktuell/" + name;
        return url;
    }

    private boolean isVectorTheme(){
        boolean isVec = parent.getTablesInfo() != null && parent.getTablesInfo().size() > 0;
        return isVec;
    }

    public String getName(){
        String name = inner.getName();

        if(isVectorTheme())
            name += " (in Zip)";

        name += ": " + parent.getTitle();

        return name;
    }

    public String getVersion(){
        if(inner.getAbbreviation() == null)
            return null;

        String res = "-";

        if(inner.getAbbreviation().equals(XTF_ABBREVIATION))
            res = "2";
        else if(inner.getAbbreviation().equals(ITF_ABBREVIATION))
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
