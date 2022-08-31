package ch.so.agi.meta2file.model.geocat;

import ch.so.agi.meta2file.libmain.BaseUrl;
import ch.so.agi.meta2file.model.*;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class to expose and transform the properties needed for geocat.
 */
public class ThemePublicationGC {

    private ThemePublication inner;
    private List<FileFormatGC> fileFormats;
    private OfficeGC ownerOrg;
    private OfficeGC serviceOrg;

    public ThemePublicationGC(ThemePublication tp){
        this.inner = tp;

        fileFormats = new ArrayList<>();
        for(FileFormat inner : tp.getFileFormats()){
            fileFormats.add(new FileFormatGC(inner, tp));
        }

        this.ownerOrg = new OfficeGC(tp.getOwner());
        this.serviceOrg = new OfficeGC(tp.getServicer());
    }

    public String getTitle(){ return inner.getTitle(); }

    public String getDescription(){ return inner.getShortDescription(); }

    public String getLastPublished(){
        if(inner.getLastPublishingDate() == null)
            return null;

        String res = inner.getLastPublishingDate().format(DateTimeFormatter.ISO_LOCAL_DATE);

        return res;
    }

    public List<FileFormatGC> getFileFormats() {
        return fileFormats;
    }

    public String getDataAppPageUrl(){
        URI full = BaseUrl.DATA_APP.getBaseUrlAsUri().resolve("?search=" + inner.getIdentifier());

        return full.toString();
    }

    public Office getDataOwnerOrg(){
        return inner.getOwner();
    }

    public Office getDataServiceOrg(){
        return inner.getServicer();
    }

    public OfficeGC getOwnerOrg() {
        return ownerOrg;
    }

    public OfficeGC getServiceOrg() {
        return serviceOrg;
    }

    public List<String> getKeysAndSynos(){
        boolean noKeys = inner.getKeywordsList() == null || inner.getKeywordsList().size() == 0;
        boolean noSynos = inner.getSynonymsList() == null || inner.getSynonymsList().size() == 0;

        if(noKeys && noSynos)
            return null;

        ArrayList<String> res = new ArrayList<>();

        if(!noKeys)
            res.addAll(inner.getKeywordsList());

        if(!noSynos)
            res.addAll(inner.getSynonymsList());

        return res;
    }

    public boolean getHasDirectPortalDownloads(){
        return inner.getItems().size() == 1;
    }

    public String getPreviewUrl(){
        return inner.getPreviewUrl();
    }
}
