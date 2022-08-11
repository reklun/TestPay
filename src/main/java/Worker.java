import dataObject.ResultRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import processor.WorkFlowProcessor;
import util.Constant;

import java.io.IOException;
import java.util.List;

public class Worker {

    private static final Logger LOGGER = LogManager.getLogger(Worker.class);

    public static void main(String[] args) throws IOException {
        // loading input files from resources
        String inputDir = Constant.INPUT_DIR;

        LOGGER.info("Start of the program");
        WorkFlowProcessor main = new WorkFlowProcessor();
        List<ResultRecord> results = main.process(inputDir);

        // can expose these variables to other interface
        String outputDir = Constant.OUTPUT_DIR;
        String outputFileName = Constant.OUTPUT_FILE_NAME;
        main.generateOutput(results, outputDir, outputFileName);
        LOGGER.info("Program End");
    }

}
