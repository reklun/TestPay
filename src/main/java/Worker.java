import dataObject.RawRecord;
import dataObject.TravelRecord;
import processor.CSVParser;
import processor.PairingProcessor;
import util.Constant;
import processor.ResourceLoader;
import processor.Transformer;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class Worker {

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {

        // loading input files from resources
        String inputDir = ResourceLoader.getPathFromResource(Constant.INPUT_DIR);

        // parsing raw data from csv files
        List<RawRecord> rawRecords = CSVParser.parseRecordsFromDir(inputDir);

        // filtering and transforming data from files
        List<TravelRecord> records = Transformer.transform(rawRecords);

        // First pairing
        Map<String, Map<Long, List<TravelRecord>>> firstPairing = PairingProcessor.pairing(records);

        for (Map.Entry<String, Map<Long, List<TravelRecord>>> entry: firstPairing.entrySet()) {
            System.out.println("key: " + entry.getKey() + "| value: " + entry.toString());
        }
    }

}
