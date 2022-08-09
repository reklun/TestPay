package util;

import dataObject.RawRecord;
import org.junit.Assert;
import org.junit.Test;
import processor.CSVParser;
import processor.ResourceLoader;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class ParserTest {

    @Test // testing the parsing of a raw file
    public void testRawParsing() throws URISyntaxException, FileNotFoundException {
        String testCsv = "src/test/resources/input-1.csv";
        int expected = 3;

        List<RawRecord> rawRecords = CSVParser.parseRecordsFromFile(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }

    @Test // testing the parsing of a directory
    public void testDirRawParsing() throws URISyntaxException, FileNotFoundException {
        String testCsv = "src/test/resources";
        int expected = 6;

        List<RawRecord> rawRecords = CSVParser.parseRecordsFromDir(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }
}
