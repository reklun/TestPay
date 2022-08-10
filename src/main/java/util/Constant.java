package util;

public class Constant {
    // supposed to be in a config file for easy management
    public static final String INPUT_DIR = "src/main/resources/input/";
    public static final String OUTPUT_DIR = "src/main/resources/output/";
    public static final String OUTPUT_FILE_NAME = "trips.csv";

    // default expiry time in hours if touch off doesnt happen
    // should be configurable based on route
    public static final Integer EXPIRY = 4;

    public static final String DOLLAR = "$";

    public static final String UNDEFINED = "UNDEFINED";
}
