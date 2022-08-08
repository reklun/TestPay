package util;

import DataObject.RawRecord;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * CSV parser from raw csv file
 */
public class CSVParser {

    public static List<RawRecord> parseRecordsFromFile(String fileName) throws FileNotFoundException {
        List<RawRecord> travelRecords = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(RawRecord.class)
                .build()
                .parse();

        return travelRecords;
    }
}
