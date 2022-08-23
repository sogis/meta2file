package ch.so.agi.meta2file.out;

import ch.so.agi.meta2file.model.ThemePublication;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;

public class MetaBean2FileConverterTest {

    @Test
    public void runBeanToXml_OK(){
        ArrayList<ThemePublication> pubs = new ArrayList<>();
        MetaBean2FileConverter.runBeans2Xml(Path.of("fuu"), pubs.iterator());
    }
}
