package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.test.JsonFileUtil;
import ch.so.agi.meta2file.test.Util;
import ch.so.agi.meta2file.test.ValueOccurence;
import org.junit.jupiter.api.Test;

import java.util.List;

class Meta2HtmlTest {

    @Test
    public void vecSunny_OK(){
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_SUNNY);

        String html = Meta2Html.renderDataDescription(json);
        List<String> keys = ValueOccurence.descriptionAllKeys();

        Util.assertContains(html, keys);
    }

    @Test
    public void vecNoOptionals_OK(){
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_NO_OPTIONALS);

        String html = Meta2Html.renderDataDescription(json);
        List<String> keys = ValueOccurence.descriptionMandatoryKeys();

        Util.assertContains(html, keys);
    }
}