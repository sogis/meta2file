package ch.so.agi.meta2file.appmain;

import ch.so.agi.meta2file.test.DbUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.Arrays;

public class Meta2File_CliTest {

    private static final String ERR_CODE_EXPECTED = "Expecting error exit code";
    private static final String OK_CODE_EXPECTED = "Expecting ok exit code (=0)";

    static {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel","info");
    }

    private static final String[] PARA_TEMPLATE = new String[]{
            "-c", DbUtil.TSTDB_URL,
            "-u", DbUtil.TSTDB_USER,
            "-p", DbUtil.TSTDB_PASS,
            "-g", "/tmp",
            "-e", "p"
    };

    private static final int PARA_P_KEY_IDX = 4;
    private static final int PARA_OUT_KEY_IDX = 6;
    private static final int PARA_OUT_VAL_IDX = 7;

    @Test
    public void missingParam_ErrCode() {
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length - 1);

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertNotEquals(0, exit, ERR_CODE_EXPECTED);
    }

    @Test
    public void invalidConn_ErrCode() {
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertNotEquals(0, exit, ERR_CODE_EXPECTED);
    }

    @Test
    public void wrongArgName_ErrCode() {
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);
        args[PARA_P_KEY_IDX] = "-x";

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertNotEquals(0, exit, ERR_CODE_EXPECTED);
    }

    @Test
    public void missingParentFolder_AppExport_ErrCode() {
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);
        args[PARA_OUT_KEY_IDX] = "-d";
        args[PARA_OUT_VAL_IDX] = "/fuu/bar/buz";

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertNotEquals(0, exit, ERR_CODE_EXPECTED);
    }

    @Test
    public void missingParentFolder_CatExport_ErrCode() {
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);
        args[PARA_OUT_KEY_IDX] = "-g";
        args[PARA_OUT_VAL_IDX] = "/fuu/bar/buz";

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertNotEquals(0, exit, ERR_CODE_EXPECTED);
    }

    @Test
    public void existingFolder_CatExport_OkCode() throws Exception{
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);
        args[PARA_OUT_KEY_IDX] = "-g";
        args[PARA_OUT_VAL_IDX] = Files.createTempDirectory("existingFolder_CatExport").toString();

        DbUtil.overrideWithTestQuery();

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertEquals(0, exit, OK_CODE_EXPECTED);
    }

    @Test
    public void existingFile_AppExport_OkCode() throws Exception{
        String[] args = Arrays.copyOf(PARA_TEMPLATE, PARA_TEMPLATE.length);
        args[PARA_OUT_KEY_IDX] = "-d";
        args[PARA_OUT_VAL_IDX] = Files.createTempFile("existingFile_AppExport", "xml").toString();

        DbUtil.overrideWithTestQuery();

        int exit = Meta2File.mainWithExitCode(args);

        Assertions.assertEquals(0, exit, OK_CODE_EXPECTED);
    }
}
