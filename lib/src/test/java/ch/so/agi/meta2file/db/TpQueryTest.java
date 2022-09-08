package ch.so.agi.meta2file.db;

import ch.so.agi.meta2file.in.db.TpIterator;
import ch.so.agi.meta2file.in.db.TpQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TpQueryTest {

    @Test
    public void allTpQuery_OK(){
        String sql = TpQuery.singleton().queryForAllThemePubs();

        Assertions.assertTrue(sql.contains(TpIterator.JSON_COL_NAME), "Loaded base query must return the json col");
    }

    @Test
    public void oneTpQuery_OK(){

        UUID testUid = UUID.randomUUID();

        String sql = TpQuery.singleton().queryForOneThemePub(testUid);

        Assertions.assertTrue(sql.contains(TpIterator.ID_COL_NAME), "Loaded base query must contain filter on tp id");
    }

    @Test
    public void queryOverride_OK(){
        String override = "select fuu from bar;";

        TpQuery.singleton().overrideQuery(override);
        String sql = TpQuery.singleton().queryForAllThemePubs();

        Assertions.assertEquals(override, sql, "Override sql must be returned");
    }

}