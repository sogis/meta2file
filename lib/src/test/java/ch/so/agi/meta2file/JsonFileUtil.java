package ch.so.agi.meta2file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Helper to keep overview over all configured json
 * test files and return their content as string.
 */
public class JsonFileUtil {

    public static final String BASE_PATH = "src/test/resources/json";

    public enum FileKeys {
        VEC_SUNNY // Complete but small-sized example json containing two classes
        ,VEC_NO_GEOM // One class without geometry
        ,VEC_NO_OPTIONALS // One class with geometry missing all optional values
    }

    public static String getFileContent(FileKeys fileKey){

        String res = null;

        String filename = fileKey.name().toLowerCase() + ".json";

        Path file = Path.of(BASE_PATH, filename).toAbsolutePath();
        try {
            byte[] dat = Files.readAllBytes(file);
            res = new String(dat, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    /*
    *     public static String loadUtf8(String resourcePath) {

    	String fileText;

    	if(resourcePath == null)
    		return null;

    	ResourceLoader resourceLoader = new DefaultResourceLoader();
    	Resource resource = resourceLoader.getResource(resourcePath);

        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            fileText = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new ResponseStatusException(
            							HttpStatus.INTERNAL_SERVER_ERROR,
            							"Could not find file " + resource.toString(),
            							e);
        }

        return fileText;
    }
    * */
}
