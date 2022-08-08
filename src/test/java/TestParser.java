import DataObject.RawRecord;
import org.junit.Assert;
import org.junit.Test;
import util.CSVParser;
import util.ResourceLoader;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class TestParser {

    /**
     * testing the parsing of raw file
     */
    @Test
    public void testRawParsing() throws URISyntaxException, FileNotFoundException {
        String csv = "input-1.csv";
        int expected = 3;

        List<RawRecord> rawRecords = CSVParser.parseRecordsFromFile(ResourceLoader.getFileFromResource(csv));

        Assert.assertEquals(expected, rawRecords.size());
//        rawRecords.forEach(System.out::println);
    }
}
