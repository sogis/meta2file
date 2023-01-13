package ch.so.agi.meta2file.test;

import ch.so.agi.meta2file.in.db.TpQuery;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DbUtil {

    public static final String TSTDB_URL = "jdbc:tc:postgresql:14:///simi_mock?TC_INITFUNCTION=" +  DbUtil.class.getName()  + "::initTestTable";
    public static final String TSTDB_USER = "test";
    public static final String TSTDB_PASS = "test";

    public static final UUID TSTTBL_TP_UID = UUID.randomUUID();
    private static final int TSTTBL_NUM_ROWS = 2;

    public static final String SQL_BASE_PATH = "src/test/resources/sql/";

    public static void initTestTable(Connection con) throws SQLException {

        String ddlSql = Util.getTextFileContent(Path.of(SQL_BASE_PATH, "testTable_DDL.sql"));

        try(PreparedStatement ddl = con.prepareStatement(ddlSql)){
            ddl.execute();
        }

        String insSql = Util.getTextFileContent(Path.of(SQL_BASE_PATH, "testTable_Insert.sql"));
        String json = Util.getFileContent(InputType.VEC_ALL);

        for(int i=0; i<TSTTBL_NUM_ROWS;i++) {

            try (PreparedStatement ins = con.prepareStatement(insSql)) {
                ins.setString(1, json);

                if(i==0) // Set specific test uuid on index 0
                    ins.setString(2, TSTTBL_TP_UID.toString());
                else
                    ins.setString(2, UUID.randomUUID().toString());

                ins.setBoolean(3, true);

                ins.execute();
            }
        }
    }

    public static Connection createTestDb() throws Exception {
        return DriverManager.getConnection(TSTDB_URL, TSTDB_USER, TSTDB_PASS);
    }

    public static void overrideWithTestQuery(){
        String testQuery = Util.getTextFileContent(Path.of(SQL_BASE_PATH, "testTable_Select.sql"));
        TpQuery.singleton().overrideQuery(testQuery);
    }
}
