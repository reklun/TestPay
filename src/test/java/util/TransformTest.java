package util;

import dataObject.RawRecord;
import dataObject.StopID;
import dataObject.Tap;
import dataObject.TravelRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processor.CSVParser;
import processor.ResourceLoader;
import processor.Transformer;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

public class TransformTest {

    private final String csv = "src/test/resources/input-1.csv";
    private List<RawRecord> rawRecords;
    private List<TravelRecord> travelRecords;

    @Before
    public void before() throws URISyntaxException, FileNotFoundException {

        rawRecords = CSVParser.parseRecordsFromFile(csv);
        travelRecords = Transformer.transform(rawRecords);
    }

    @Test // testing the transformation of raw record
    public void testRawParsing() throws URISyntaxException, FileNotFoundException {
        int expectedRaw = 3;
        int expectedTravel = 2;

        List<RawRecord> rawRecords = CSVParser.parseRecordsFromFile(csv);
        Assert.assertEquals(expectedRaw, rawRecords.size());

        List<TravelRecord> travelRecords = Transformer.transform(rawRecords);
        Assert.assertEquals(expectedTravel, travelRecords.size());
    }

    @Test // test one transformed record
    public void testTransform() {
        // 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
        TravelRecord travelRecord = travelRecords.get(0);

        Assert.assertEquals(1, travelRecord.getId());
        Assert.assertEquals(LocalDateTime.parse("22-01-2018 13:00:00", DateTimeUtil.DATE_TIME_FORMATTER), travelRecord.getDateTimeUTC());
        Assert.assertEquals(Tap.ON, travelRecord.getTapType());
        Assert.assertEquals(StopID.STOP1, travelRecord.getStopId());
        Assert.assertEquals("Bus37", travelRecord.getBusID());
        Assert.assertEquals("5500005555555559", travelRecord.getPan());
    }
}
