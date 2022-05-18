package ch.so.agi.metabean2file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Util {
    public static void loadFile(String resourcePath, File copyFile) throws IOException {
        InputStream is = Util.class.getClassLoader().getResourceAsStream(resourcePath);
        Files.copy(is, copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    public static String loadUtf8(String resourcePath) throws IOException {
        InputStream is = Util.class.getClassLoader().getResourceAsStream(resourcePath);        
        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        return content;
    }
}
