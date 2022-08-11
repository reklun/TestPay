package handler;

import dataObject.RawRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class CSVParserTest {

    @Test // testing the parsing of a raw file
    public void testRawParsing() throws FileNotFoundException {
        final String testCsv = "src/test/resources/general/input-1.csv";
        final int expected = 3;

        List<RawRecord> rawRecords = CSVReadHandler.parseRecordsFromFile(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }

    @Test // testing the parsing of a directory
    public void testDirRawParsing() throws FileNotFoundException {
        final String testCsv = "src/test/resources/general";
        final int expected = 6;

        List<RawRecord> rawRecords = CSVReadHandler.parseRecordsFromDir(testCsv);

        Assert.assertEquals(expected, rawRecords.size());
    }
}
