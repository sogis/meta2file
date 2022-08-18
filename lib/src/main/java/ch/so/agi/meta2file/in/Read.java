package ch.so.agi.meta2file.in;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.db.TpIterator;
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

        return res;
    }

    public static ThemePublication fromMetaDb(Connection con, UUID themePubUid){
        TpIterator iter = new TpIterator(con, themePubUid);

        if(!iter.hasNext())
            throw new Meta2FileException("No records returned for themepub {0}.", themePubUid);

        return iter.next();
    }
}
