package ch.so.agi.meta2file.in.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TpQueryTest {

    @Test
    public void allTpQuery_OK(){
        TpQuery q = TpQuery.singleton();
        q.setToDefaultQuery();

        String sql = q.queryForPortalPublishedThemePubs();

        Assertions.assertTrue(sql.contains(TpQuery.JSON_COL_NAME), "Loaded base query must return the json col");
    }

    @Test
    public void oneTpQuery_OK(){
        TpQuery q = TpQuery.singleton();
        q.setToDefaultQuery();

        UUID testUid = UUID.randomUUID();

        String sql = q.queryForOneThemePub(testUid);

        Assertions.assertTrue(sql.contains(TpQuery.ID_COL_NAME), "Loaded base query must contain filter on tp id");
    }

    @Test
    public void queryOverride_OK(){
        String override_unclosed = "select fuu from bar";

        TpQuery q = TpQuery.singleton();
        q.overrideQuery(override_unclosed + ";");

        String sql = q.queryForPortalPublishedThemePubs();

        Assertions.assertTrue(sql.contains(override_unclosed), "Override sql must be returned");
    }

}