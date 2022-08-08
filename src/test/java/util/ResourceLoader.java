package util;

import java.net.URISyntaxException;
import java.net.URL;

public class ResourceLoader {

    /**
     * Returning a file path from test resource
     * @param fileName
     * @return
     * @throws URISyntaxException
     */
    public static String getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return resource.toURI().getPath();
        }
    }
}
