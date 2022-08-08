import DataObject.RawRecord;
import DataObject.TravelRecord;
import org.junit.Assert;
import org.junit.Test;
import util.CSVParser;
import util.ResourceLoader;
import util.Transformer;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class TestTransform {

    /**
     * testing the transformation of raw record
     */
    @Test
    public void testRawParsing() throws URISyntaxException, FileNotFoundException {
        String csv = "input-1.csv";
        int expectedRaw = 3;
        int expectedTravel = 2;

        List<RawRecord> rawRecords = CSVParser.parseRecordsFromFile(ResourceLoader.getFileFromResource(csv));
        Assert.assertEquals(expectedRaw, rawRecords.size());

        List<TravelRecord> travelRecords = Transformer.transform(rawRecords);
        Assert.assertEquals(expectedTravel, travelRecords.size());

//        rawRecords.forEach(System.out::println);
//        travelRecords.forEach(System.out::println);
    }
}
