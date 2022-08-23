package ch.so.agi.meta2file.appmain;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.json.Deserializer;
import ch.so.agi.meta2file.libmain.Meta2Html;
import ch.so.agi.meta2file.model.ThemePublication;
import ch.so.agi.meta2file.out.MetaBean2FileConverter;
import ch.so.agi.meta2file.test.JsonFileUtil;
import ch.so.agi.meta2file.test.Util;
import ch.so.agi.meta2file.test.ValueOccurence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Meta2FileTest {

    private static Connection con;

    @Test()
    public void exportAppFile_withFolder_Throws(){
        assertThrows(
                Meta2FileException.class,
                () -> Meta2File.exportAppFile(con, new File("/tmp")))
        ;
    }

    @Test
    public void exportToFolder_withFilePath_Throws(){
        assertThrows(
                Meta2FileException.class,
                () -> Meta2File.exportGeocatFiles(con, new File("/tmp/dummy.xml")))
        ;
    }

    @Test
    public void exportAppFile_withSunnyJson_OK() throws Exception {
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_SUNNY);

        ThemePublication tp = Deserializer.fromString(json);
        Iterator<ThemePublication> iter = Util.iterFromBean(tp, 2);

        Path dir = Files.createTempDirectory("app_sunny");
        Path file = dir.resolve("conf.xml");

        MetaBean2FileConverter.runBeans2Xml(file, iter);

        String xml = Util.getTextFileContent(file);
        List<String> keys = ValueOccurence.appAllKeys();

        Util.assertContains(xml, keys);
    }

    @BeforeAll
    static void beforeAll() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql:postgres",
                    "postgres",
                    "postgres"
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @AfterAll
    static void afterAll() {
        if(con == null)
            return;

        try {
            con.close();
        } catch (SQLException ignored) {}
    }
}