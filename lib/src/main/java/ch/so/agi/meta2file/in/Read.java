package ch.so.agi.meta2file.in;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.db.TpIterator;
import ch.so.agi.meta2file.libmain.BaseUrl;
import ch.so.agi.meta2file.model.Layer;
import ch.so.agi.meta2file.model.Service;
import ch.so.agi.meta2file.model.ServiceType;
import ch.so.agi.meta2file.model.ThemePublication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.sql.Connection;
import java.util.UUID;

public class Read {

    private static ObjectMapper mapper;

    static{
        mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    public static ThemePublication fromJson(String json){
        ThemePublication res = null;

        try {
            res = mapper.readValue(json, ThemePublication.class);
        } catch (JsonProcessingException e) {
            throw new Meta2FileException(e);
        }

        deriveWgcPreview(res);
        setServiceEndpointUrls(res);

        return res;
    }

    private static void setServiceEndpointUrls(ThemePublication res){

        if(res.getServices() == null)
            return;

        for(Service s : res.getServices()){
            if(s.getType() == ServiceType.DATA)
                s.setEndpoint(BaseUrl.DATA_SERVICE.getBaseUrlAsUri());
            else if(s.getType() == ServiceType.WFS)
                s.setEndpoint(BaseUrl.WFS.getBaseUrlAsUri());
            else if(s.getType() == ServiceType.WGC)
                s.setEndpoint(BaseUrl.WGC.getBaseUrlAsUri());
            else if(s.getType() == ServiceType.WMS)
                s.setEndpoint(BaseUrl.WMS.getBaseUrlAsUri());
            else
                throw new Meta2FileException("No base url defined for service type {0}", s.getType());
        }
    }

    private static void deriveWgcPreview(ThemePublication res) {
        if(res.getServices() == null || res.getServices().size() == 0)
            return;

        Service wgc = null;

        for(Service s : res.getServices()){
            if(s.getType() == ServiceType.WGC){
                wgc = s;
                break;
            }
        }

        if(wgc == null)
            return;

        for(Layer l : wgc.getLayers()){
            if(res.getIdentifier().equals(l.getIdentifier())){
                res.setWgcPreviewLayer(l);
                break;
            }
        }
    }

    public static ThemePublication fromMetaDb(Connection con, UUID themePubUid){
        TpIterator iter = new TpIterator(con, themePubUid);

        if(!iter.hasNext())
            throw new Meta2FileException("No records returned for themepub {0}.", themePubUid);

        return iter.next();
    }
}
