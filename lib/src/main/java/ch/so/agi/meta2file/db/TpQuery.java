package ch.so.agi.meta2file.db;

public class TpQuery {
    public static String queryForAllThemePubs(){
        return null;
    }

    /**
     * Sql select with whereclause on uuid param "tp_id"
     * @return The query with whereclause
     */
    public static String queryForOneThemePub(){
        return queryTemplate() + " where tp_id = '?';";
    }

    /**
     * Returns the Query without trailing ";"
     * @return Query-Template
     */
    private static String queryTemplate(){
        return null;
    }
}
