package ch.so.agi.meta2file.in.db;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.Read;
import ch.so.agi.meta2file.model.ThemePublication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Iterator;
import java.util.UUID;

/**
 * Iterates over the db resultset. Each iteration
 * returns a ThemePublication bean to the consumer.
 */
public class TpIterator implements Iterator<ThemePublication> {

    private static final Logger log = LoggerFactory.getLogger(TpIterator.class);

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
                sql = TpQuery.singleton().queryForPortalPublishedThemePubs();
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
                throw new Meta2FileException("Resultset has no more rows. Use hasNext() to break your iteration properly.");

            String jsonString = rs.getString(TpQuery.JSON_COL_NAME);
            res = Read.fromJson(jsonString);

            log.info("Processing {}", res.getIdentifier());
        } catch (SQLException throwables) {
            throw new Meta2FileException(throwables);
        }

        return res;
    }
}
