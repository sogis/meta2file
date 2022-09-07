package ch.so.agi.meta2file.db;

import ch.so.agi.meta2file.in.db.TpIterator;

import java.text.MessageFormat;
import java.util.UUID;

public class TpQuery {

    private static TpQuery singleton = new TpQuery();

    public static TpQuery singleton(){
        return singleton;
    }

    private String baseQuery = null;

    private TpQuery(){
        this.baseQuery = MessageFormat.format("select {0}, {1} from simi.meta_themepub_v;", TpIterator.JSON_COL_NAME, TpIterator.ID_COL_NAME);
    }

    public String queryForAllThemePubs(){
        return baseQuery;
    }

    /**
     * Sql select with whereclause on uuid param "tp_id"
     * @return The query with whereclause
     */
    public String queryForOneThemePub(UUID themePubUid){

        int endStatementIdx = baseQuery.lastIndexOf(";");

        String queryTemplate = baseQuery.substring(0,endStatementIdx);
        String where = MessageFormat.format("where {0} = ''{1}'';", TpIterator.ID_COL_NAME, themePubUid);

        return queryTemplate + " " + where;
    }

    public void overrideQuery(String newQuery){
        baseQuery = newQuery;
    }
}
