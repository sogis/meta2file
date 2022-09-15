package ch.so.agi.meta2file.in.db;

import java.text.MessageFormat;
import java.util.UUID;

public class TpQuery {

    public static final String ID_COL_NAME = "tp_id";
    public static final String JSON_COL_NAME = "tp_json";

    private static final String QUERY =
            MessageFormat.format("select {0}, {1} from simi.app_themepub_v;", JSON_COL_NAME, ID_COL_NAME);

    private static TpQuery singleton = new TpQuery();

    public static TpQuery singleton(){
        return singleton;
    }

    private String baseQuery = null;

    private TpQuery(){
        setToDefaultQuery();
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
        String where = MessageFormat.format("where {0} = ''{1}'';", ID_COL_NAME, themePubUid);

        return queryTemplate + " " + where;
    }

    public void overrideQuery(String newQuery){
        baseQuery = newQuery;
    }

    public void setToDefaultQuery(){
        this.baseQuery = QUERY;
    }
}
