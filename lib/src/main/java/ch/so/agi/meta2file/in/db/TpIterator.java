package ch.so.agi.meta2file.in.db;

import ch.so.agi.meta2file.db.TpQuery;
import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.Read;
import ch.so.agi.meta2file.model.ThemePublication;

import java.sql.*;
import java.util.Iterator;
import java.util.UUID;

/**
 * Iterates over the db resultset. Each iteration
 * returns a ThemePublication bean to the consumer.
 */
public class TpIterator implements Iterator<ThemePublication> {

    public static final String ID_COL_NAME = "tp_id";
    public static final String JSON_COL_NAME = "tp_json";

    private static final int FETCH_SIZE = 10;

    private Connection con;
    private UUID themePubUid;

    private ResultSet rs;

    public TpIterator(Connection con){
        this(con, null);
    }

    public TpIterator(Connection con, UUID themePubUid){

        this.con = con;
        this.themePubUid = themePubUid;
    }

    @Override
    public boolean hasNext() {

        if(rs == null)
            initResultSet();

        boolean res = false;

        try{
            res = !rs.isLast();
        }
        catch (SQLException se){
            throw new RuntimeException(se);
        }

        return res;
    }

    private void initResultSet() {
        try {
            String sql = null;
            if(themePubUid == null)
                sql = TpQuery.singleton().queryForAllThemePubs();
            else
                sql = TpQuery.singleton().queryForOneThemePub(themePubUid);

            PreparedStatement s = con.prepareStatement(sql);
            s.setFetchSize(FETCH_SIZE);
            this.rs = s.executeQuery();
        } catch (SQLException se) {
            throw new Meta2FileException(se);
        }
    }

    @Override
    public ThemePublication next() {

        ThemePublication res = null;

        if(rs == null)
            initResultSet();

        try {
            boolean couldIterate = rs.next();
            if(!couldIterate)
                throw new Meta2FileException("Resultset is exhausted. Use hasNext() to break your iteration.");

            String jsonString = rs.getString(JSON_COL_NAME);
            res = Read.fromJson(jsonString);
        } catch (SQLException throwables) {
            throw new Meta2FileException(throwables);
        }

        return res;
    }
}
