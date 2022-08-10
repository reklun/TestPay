package util;

import dataObject.RawRecord;
import org.junit.Assert;
import org.junit.Test;
import handler.CSVReadHandler;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

public class CSVParserTest {

    @Test // testing the parsing of a raw file
    public void testRawParsing() throws URISyntaxException, FileNotFoundException {
        String testCsv = "src/test/resources/input-1.csv";
        int expected = 3;

        List<RawRecord> rawRecords = CSVReadHandler.parseRecordsFromFile(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }

    @Test // testing the parsing of a directory
    public void testDirRawParsing() throws URISyntaxException, FileNotFoundException {
        String testCsv = "src/test/resources";
        int expected = 6;

        List<RawRecord> rawRecords = CSVReadHandler.parseRecordsFromDir(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }
}
