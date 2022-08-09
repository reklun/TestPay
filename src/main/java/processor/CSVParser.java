package processor;

import dataObject.RawRecord;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV parser from raw csv file
 */
public class CSVParser {

    /**
     * loop through a list of files and return all records from all files
     *
     * @param dirPath
     * @return
     * @throws FileNotFoundException
     */
    public static List<RawRecord> parseRecordsFromDir(String dirPath) throws FileNotFoundException {
        List<RawRecord> travelRecords = new ArrayList<>();

        File folder = new File(dirPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                travelRecords.addAll(parseRecordsFromFile(listOfFiles[i].getAbsolutePath()));
            }
        }

        return travelRecords;
    }

    /**
     * return list of raw records from a file
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static List<RawRecord> parseRecordsFromFile(String fileName) throws FileNotFoundException {
        List<RawRecord> travelRecords = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(RawRecord.class)
                .build()
                .parse();

        return travelRecords;
    }
}
