package processor;

import dataObject.RawRecord;
import dataObject.ResultRecord;
import dataObject.TravelRecord;
import handler.CSVReadHandler;
import handler.CSVWriteHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Constant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * the main processor for this program
 */
public class MainProcessor {

    private static final Logger LOGGER = LogManager.getLogger(MainProcessor.class);

    public MainProcessor() {

    }

    public void process() throws IOException {
        LOGGER.info("Start of the program");

        // loading input files from resources
        String inputDir = Constant.INPUT_DIR;

        // parsing raw data from csv files
        LOGGER.info("Start parsing");
        List<RawRecord> rawRecords = CSVReadHandler.parseRecordsFromDir(inputDir);

        // filtering and transforming data from files
        LOGGER.info("Transforming raw records");
        List<TravelRecord> records = Transformer.transform(rawRecords);

        // Pairing
        LOGGER.info("Pairing records");
        Map<String, Map<Long, List<TravelRecord>>> pairedMap = PairingProcessor.pairing(records);

        // Generating a list of records
        LOGGER.info("Preparing output");
        List<ResultRecord> results = ResultProcessor.buildResult(pairedMap);

        // Write to CSV file
        LOGGER.info("Generating CSV output");
        CSVWriteHandler.csvWriter(results, Constant.OUTPUT_DIR + Constant.OUTPUT_FILE_NAME);

        LOGGER.info("Program End");
    }
}
