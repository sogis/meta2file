package ch.so.agi.meta2file.appmain;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.model.ThemePublication;
import ch.so.agi.meta2file.out.MetaBean2FileConverter;
import ch.so.agi.meta2file.out.geocat.Geocat;
import ch.so.agi.meta2file.test.InputType;
import ch.so.agi.meta2file.test.OutputType;
import ch.so.agi.meta2file.test.Util;
import ch.so.agi.meta2file.test.ValueOccurence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contain all tests with source = json
 */
class Meta2File_JsonTest {

    private static Logger log = LoggerFactory.getLogger(Meta2File_JsonTest.class);

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
    public void app_vecAll_OK() throws Exception {
        String xml = appJsonTest(InputType.VEC_ALL);
        Util.assertContains(xml, "wgcPreviewLayer");
    }

    @Test
    public void app_vecMandatory_OK() throws Exception {
        appJsonTest(InputType.VEC_MANDATORY);
    }

    @Test
    public void app_otherAll_OK() throws Exception {
        String xml = appJsonTest(InputType.OTHER_ALL);
        Util.assertContains(xml, "wgcPreviewLayer");
    }

    @Test
    public void app_otherMandatory_OK() throws Exception {
        appJsonTest(InputType.OTHER_MANDATORY);
    }

    @Test
    public void geocat_vecAll_OK() throws Exception {
        catJsonTest(InputType.VEC_ALL);
    }

    @Test
    public void geocat_otherAll_OK() throws Exception {
        catJsonTest(InputType.OTHER_ALL);
    }

    @Test
    public void geocat_vecMandatory_OK() throws Exception {
        catJsonTest(InputType.VEC_MANDATORY);
    }

    @Test
    public void geocat_otherMandatory_OK() throws Exception {
        catJsonTest(InputType.OTHER_MANDATORY);
    }

    @Test
    public void vecRealdata_singleregion_OK() throws Exception {
        realDataTest(InputType.VEC_REALDATA_SINGLEREGION);
    }

    @Test
    public void vecRealdata_multiregion_OK() throws Exception {
        realDataTest(InputType.VEC_REALDATA_MULTIREGION);
    }

    private static void realDataTest(InputType inputType) throws Exception {
        List<ThemePublication> beanList = Util.createTwoElemListFromJson(inputType);

        Path dir = Files.createTempDirectory(inputType.deferPathPartName());
        Path file = dir.resolve("conf.xml");

        MetaBean2FileConverter.runBeans2Xml(file, beanList.iterator());
        MetaBean2FileConverter.runBean2Html(beanList.get(0));

        String outBaseName = "geocat_" + inputType.deferPathPartName() + "_";
        Path catDir = Files.createTempDirectory(outBaseName);

        Geocat.beans2Files(catDir, beanList.iterator());
    }

    private static String appJsonTest(InputType inType) throws Exception {
        List<ThemePublication> beanList = Util.createTwoElemListFromJson(inType);

        Path dir = Files.createTempDirectory("app_" + inType.deferPathPartName());
        Path file = dir.resolve("conf.xml");

        MetaBean2FileConverter.runBeans2Xml(file, beanList.iterator());

        String xml = Util.getTextFileContent(file);
        List<String> keys = ValueOccurence.keysForTest(inType, OutputType.APP);

        Util.assertContains(xml, keys);

        return xml;
    }

    private static String catJsonTest(InputType inType) throws Exception {
        List<ThemePublication> beanList = Util.createTwoElemListFromJson(inType);

        String outBaseName = "geocat_" + inType.deferPathPartName() + "_";
        Path dir = Files.createTempDirectory(outBaseName);

        Geocat.beans2Files(dir, beanList.iterator());

        Path file = dir.resolve(beanList.get(0).getIdentifier() + ".xml");

        log.info("Tested iso-ch file: {}", file);

        String xml = Util.getTextFileContent(file);
        List<String> keys = ValueOccurence.keysForTest(inType, OutputType.GEOCAT);

        Util.assertContains(xml, keys);

        return xml;
    }


    //@BeforeAll
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