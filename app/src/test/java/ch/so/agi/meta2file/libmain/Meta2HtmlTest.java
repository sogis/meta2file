package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.test.InputType;
import ch.so.agi.meta2file.test.OutputType;
import ch.so.agi.meta2file.test.Util;
import ch.so.agi.meta2file.test.ValueOccurence;
import org.junit.jupiter.api.Test;

import java.util.List;

class Meta2HtmlTest {

    @Test
    public void sheet_vecAll_OK(){
        testWithInput(InputType.VEC_ALL);
    }

    @Test
    public void sheet_vecMandatory_OK(){
        testWithInput(InputType.VEC_MANDATORY);
    }

    @Test
    public void sheet_otherAll_OK(){
        testWithInput(InputType.OTHER_ALL);
    }

    @Test
    public void sheet_otherMandatory_OK(){
        testWithInput(InputType.OTHER_MANDATORY);
    }

    private static void testWithInput(InputType inType){
        String json = Util.getFileContent(inType);

        String html = Meta2Html.renderDataSheet(json, Environment.PROD);
        List<String> keys = ValueOccurence.keysForTest(inType, OutputType.SHEET);

        Util.assertContains(html, keys);
    }
}