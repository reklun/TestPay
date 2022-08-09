package processor;

import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class is for loading the resources of files
 */
public class ResourceLoader {

    /**
     * Returning a file path from test resource
     *
     * @param fileName
     * @return
     * @throws URISyntaxException
     */
    public static String getPathFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found! " + fileName);
        } else {
            return resource.toURI().getPath();
        }
    }
}
