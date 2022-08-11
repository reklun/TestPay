package processor;

import dataObject.ResultRecord;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * for looping through the test files so that it can generate output for each test case
 */
public class WorkFlowProcessorTestByInput {

    final String ARG_INPUT = "infile";

    WorkFlowProcessor workFlow = new WorkFlowProcessor();

    private boolean isParamExist() {
        String inputArgu = System.getProperty(ARG_INPUT);

        if (inputArgu == null || inputArgu.isEmpty()) {
            return false;
        }

        return true;
    }

    @Test // generate output files for a structure of test folders
    public void testByInputs() throws IOException {

        if (!isParamExist()) {
            return;
        }

        final String testDir = System.getProperty(ARG_INPUT);

        File folder = new File(testDir);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File subTestDir = listOfFiles[i];

            System.out.println(subTestDir.getAbsolutePath().toString());

            if (subTestDir.isDirectory()) {
                List<ResultRecord> results = workFlow.process(subTestDir.getAbsolutePath() + "/input/");

                workFlow.generateOutput(results, subTestDir.getAbsolutePath() + "/output/", "trips.csv");
            }
        }
    }

}
