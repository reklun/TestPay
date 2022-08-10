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
public class WorkFlowProcessor {

    private static final Logger LOGGER = LogManager.getLogger(WorkFlowProcessor.class);

    public WorkFlowProcessor() {

    }

    public List<ResultRecord> process(String inputDir) throws IOException {
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
        return ResultProcessor.buildResult(pairedMap);
    }

    public void generateOutput(List<ResultRecord> results) throws IOException {
        // Write to CSV file
        LOGGER.info("Generating CSV output");
        CSVWriteHandler.csvWriter(results, Constant.OUTPUT_DIR + Constant.OUTPUT_FILE_NAME);
    }
}
