package ch.so.agi.meta2file.libmain;

import ch.so.agi.meta2file.JsonFileUtil;
import ch.so.agi.meta2file.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Meta2HtmlTest {

    @Test
    public void vecSunny_OK(){
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_SUNNY);

        String html = Meta2Html.renderDataDescription(json);

        TestUtils.assertContains(
                html,
                new String[]{
                        "$themePub.title$"
                        ,"$themePub.shortDescription$"
                        ,"$themePub.iliModelName$"
                        ,"$owner.division$"
                        ,"$owner.agency$"
                        ,"$service.wmsLayer1$"
                        ,"$service.wmsLayer2$"
                        ,"$table1.sqlName$"
                        ,"$table1.attr1.name$"
                        ,"$table1.attr2.name$"
                        ,"$table1.shortDescription$"
                        ,"$table2.sqlName$"
                        ,"$table2.attr1.name$"
                        ,"$table2.attr2.name$"
                        ,"$table2.shortDescription$"
                }
                );
    }

    @Test
    public void vecNoOptionals_OK(){
        String json = JsonFileUtil.getFileContent(JsonFileUtil.FileKeys.VEC_NO_OPTIONALS);

        String html = Meta2Html.renderDataDescription(json);

        TestUtils.assertContains(
                html,
                new String[]{
                        "$themePub.title$"
                        ,"$themePub.shortDescription$"
                        ,"$themePub.iliModelName$"
                        ,"$owner.division$"
                        ,"$owner.agency$"
                        ,"$table1.sqlName$"
                        ,"$table1.attr1.name$"
                        ,"$table1.attr2.name$"
                        ,"$table1.shortDescription$"
                        ,"$table2.sqlName$"
                        ,"$table2.attr1.name$"
                        ,"$table2.attr2.name$"
                        ,"$table2.shortDescription$"
                }
        );
    }
}


/*
Keys in vecSunny_OK:

$themePub.identifier$
$themePub.title$
$themePub.iliModelName$
$themePub.shortDescription$

$owner.division$
$owner.agency$

$service.wmsLayer1$
$service.wmsLayer2$

$table1.title$
$table1.sqlName$
$table1.attr1.name$
$table1.attr1.alias$
$table1.attr2.name$
$table1.attr2.alias$
$table1.shortDescription$
$table2.title$
$table2.sqlName$
$table2.attr1.name$
$table2.attr1.alias$
$table2.attr2.name$
$table2.attr2.alias$
$table2.shortDescription$

$key1$
$key2$
$syno1$
$syno2$

$fileFormat1$
$fileFormat2$
*/