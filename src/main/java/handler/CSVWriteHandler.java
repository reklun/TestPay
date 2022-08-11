package handler;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import dataObject.ResultRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * for writing Java beans into CSV files
 */
public class CSVWriteHandler {

    private static final Logger LOGGER = LogManager.getLogger(CSVWriteHandler.class);

    private static StatefulBeanToCsv beanWriter;
    private static ColumnPositionMappingStrategy tripsMappingStrategy;

    /**
     * create a CSV Bean writer
     *
     * @param writer
     * @return
     */
    private static StatefulBeanToCsv getBeanWriter(Writer writer) {
        StatefulBeanToCsvBuilder<ResultRecord> builder = new StatefulBeanToCsvBuilder(writer);
        beanWriter = builder.withMappingStrategy(getTripsMappingStrategy())
                .withOrderedResults(false)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

        return beanWriter;
    }

    /**
     * Create a strategy or a style for the writer
     *
     * @return
     */
    private static ColumnPositionMappingStrategy getTripsMappingStrategy() {
        if (tripsMappingStrategy == null) {
            tripsMappingStrategy = new ColumnPositionMappingStrategy();
            tripsMappingStrategy.setType(ResultRecord.class);
            tripsMappingStrategy.setColumnMapping(ResultRecord.COLUMNS);
        }

        return tripsMappingStrategy;
    }

    /**
     * The actual writer writing the the results
     *
     * @param results
     * @throws IOException
     */
    public static void csvWriter(List<ResultRecord> results, String filePath) throws IOException {

        LOGGER.info("Starting writing to " + filePath);
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {

            // add a line of header before printing the records
            writer.write(ResultRecord.getCSVHeader());
            writer.write("\n");

            // Write list to StatefulBeanToCsv object
            getBeanWriter(writer).write(results);

            LOGGER.info("Finished writing");

        } catch (CsvRequiredFieldEmptyException e) {
            LOGGER.error("Unknown CSV error " + e.getMessage());

        } catch (CsvDataTypeMismatchException e) {
            LOGGER.error("Unknown CSV error " + e.getMessage());

        }
    }
}
