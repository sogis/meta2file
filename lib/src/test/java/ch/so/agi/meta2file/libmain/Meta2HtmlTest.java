package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.JsonFileUtil;
import ch.so.agi.meta2file.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Meta2HtmlTest {

    //@Test
    public void vecSunny_OK(){
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_SUNNY);


        String html = Meta2Html.renderDataDescription(json);

        TestUtils.assertContains(html, new String[]{"fuu"});
    }

}