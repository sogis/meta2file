package ch.so.agi.meta2file.test;

import ch.so.agi.meta2file.except.Meta2FileException;
import ch.so.agi.meta2file.in.Read;
import ch.so.agi.meta2file.libmain.Environment;
import ch.so.agi.meta2file.model.ThemePublication;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Path tempFile(String prefix, String suffix){
        Path res = null;

        try {
            res = Files.createTempFile(prefix, suffix);
        } catch (IOException e) {
            throw new Meta2FileException(e);
        }
        return res;
    }

    public static void assertContains(String wholeString, String mandatoryPart) {
        assertContains(wholeString, List.of(mandatoryPart));
    }

    public static void assertContains(String wholeString, List<String> mandatoryParts) {
        ArrayList<String> missing = new ArrayList();

        for(String part : mandatoryParts){
            if(!wholeString.contains(part)){
                missing.add(part);
            }
        }

        Assertions.assertEquals(
                0,
                missing.size(),
                MessageFormat.format("Missing string parts: {0}", missing));
    }

    public static List<ThemePublication> listFromBean(ThemePublication tp, int numRecords){
        List<ThemePublication> list = new ArrayList<>(numRecords);

        for(int i=0; i<numRecords; i++){
            list.add(tp);
        }

        return list;
    }

    public static String getTextFileContent(Path filePath){
        String res = null;

        try {
            byte[] dat = Files.readAllBytes(filePath);
            res = new String(dat, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static List<ThemePublication> createTwoElemListFromJson(InputType inType) {
        String json = getFileContent(inType);

        ThemePublication tp = Read.fromJson(json, Environment.PROD);
        List<ThemePublication> beanList = Util.listFromBean(tp, 2);

        return beanList;
    }

    public static String getFileContent(InputType fileKey){

        String res = null;

        Path file = Path.of("src/test/resources/json", fileKey.deferJsonFileName()).toAbsolutePath();
        res = Util.getTextFileContent(file);

        return res;
    }
}
