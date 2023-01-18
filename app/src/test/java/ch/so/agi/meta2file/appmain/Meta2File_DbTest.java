package ch.so.agi.meta2file.appmain;

import ch.so.agi.meta2file.libmain.Meta2Html;
import ch.so.agi.meta2file.test.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;

/**
 * Contains the end to end system tests reading from a postgresql db.
 *
 * The db is started using testcontainers (see testcontainers.org)
 */
public class Meta2File_DbTest {

    @Test
    public void appmain_AppExport_OK() throws Exception {

        Path res = Util.tempFile("main_AppExport", "xml");

        String[] args = new String[]{
                "-c", DbUtil.TSTDB_URL,
                "-u", DbUtil.TSTDB_USER,
                "-p", DbUtil.TSTDB_PASS,
                "-d", res.toAbsolutePath().toString(),
                "-e", "p"
        };

        DbUtil.overrideWithTestQuery();

        Meta2File.main(args);

        String xml = Util.getTextFileContent(res);
        List<String> keys = ValueOccurence.keysForTest(InputType.VEC_ALL, OutputType.APP);

        Util.assertContains(xml, keys);
    }

    @Test
    public void appmain_GeocatExport_OK() throws Exception {

        Path res = Files.createTempDirectory("main_GeocatExport");

        String[] args = new String[]{
                "-c", DbUtil.TSTDB_URL,
                "-u", DbUtil.TSTDB_USER,
                "-p", DbUtil.TSTDB_PASS,
                "-g", res.toAbsolutePath().toString(),
                "-e", "p"
        };

        DbUtil.overrideWithTestQuery();

        Meta2File.main(args);

        //No need to test generated xml files. This is covered by lower level tests
    }

    @Test
    public void lib_renderDataSheet_OK() throws Exception {

        String html = null;

        try(Connection con = DbUtil.createTestDb()){
            DbUtil.overrideWithTestQuery();

            html = Meta2Html.renderDataSheet(DbUtil.TSTTBL_TP_UID, con);
        }

        List<String> keys = ValueOccurence.keysForTest(InputType.VEC_ALL, OutputType.SHEET);

        Util.assertContains(html, keys);
    }
}
