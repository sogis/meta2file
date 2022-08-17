package ch.so.agi.meta2file.in.db;

import ch.so.agi.meta2file.db.TpQuery;
import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.json.Deserializer;
import ch.so.agi.meta2file.model.ThemePublication;

import java.sql.*;
import java.util.Iterator;
import java.util.UUID;

/**
 * Iterates over the db resultset. Each iteration
 * returns a ThemePublication bean to the consumer.
 */
public class TpIterator implements Iterator<ThemePublication> {

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
            Statement s = con.createStatement();
            s.setFetchSize(FETCH_SIZE);
            this.rs = s.executeQuery(TpQuery.queryForAllThemePubs());
        } catch (SQLException se) {
            throw new Meta2FileException(se);
        }
    }

    @Override
    public ThemePublication next() {
        if(rs == null)
            initResultSet();

        try {
            boolean couldIterate = rs.next();
            if(!couldIterate)
                throw new Meta2FileException("Resultset is exhausted. Use hasNext() to break your iteration.");

            String jsonString = rs.getString(1);
            ThemePublication tp = Deserializer.fromString(jsonString);
        } catch (SQLException throwables) {
            throw new Meta2FileException(throwables);
        }

        return null;
    }
}
