package util;

import DataObject.RawRecord;
import DataObject.Tap;
import DataObject.TravelRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import Exception.TransformException;

/**
 * for transforming data from raw to objects with appropriate type
 */
public class Transformer {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(Constant.DATE_TIME_FORMAT);

    /**
     * for transforming a list of raw records into a list of travel records
     * @param rawRecords
     * @return
     */
    public static List<TravelRecord> transform(List<RawRecord> rawRecords) {

        List<TravelRecord> travelRecords = new ArrayList<>();

        for(RawRecord raw: rawRecords) {
            try {
                travelRecords.add(transform(raw));
            } catch (TransformException e) {
                // TODO report a log to investigate the failure of transformation
                System.out.println(e.getMessage());
            }
        }

        return travelRecords;
    }

    /**
     * for a single object transformation
     * @param rawRecord
     * @return
     */
    private static TravelRecord transform(RawRecord rawRecord) throws TransformException {
        try {
            TravelRecord travelRecord = new TravelRecord();

            travelRecord.setId(idTransform(rawRecord.getId()));
            travelRecord.setDateTimeUTC(dateTimeTransform(rawRecord.getDateTimeUTC()));
            travelRecord.setTapType(tapTransform(rawRecord.getTapType()));
            travelRecord.setStopId(rawRecord.getStopId().trim());
            travelRecord.setBusID(rawRecord.getBusID().trim());
            travelRecord.setCompanyId(rawRecord.getCompanyId().trim());
            travelRecord.setPan(rawRecord.getPan().trim());

            return travelRecord;
        } catch (TransformException e) {
            StringBuilder sb = new StringBuilder();
            sb.append(getCurrentTime());
            sb.append(" - ");
            sb.append("Failed to transform: ");
            sb.append(rawRecord.toString());
            sb.append(" - ");
            sb.append(e.getMessage());
            throw new TransformException(sb.toString());
        }
    }

    private static int idTransform(String id) throws TransformException {
        try {
            return Integer.parseInt(id.trim());
        } catch (Exception e) {
            throw new TransformException("Unable to transform ID");
        }
    }

    private static LocalDateTime dateTimeTransform(String strDateTime) throws TransformException {
        try {
            return LocalDateTime.parse(strDateTime.trim(), DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new TransformException("Unable to transform Date Time");
        }
    }

    private static Tap tapTransform(String strTap) throws TransformException {
        try {
            return Tap.valueOf(strTap.trim());
        } catch (Exception e) {
            throw new TransformException("Unable to transform Tap");
        }
    }

    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return DATE_TIME_FORMATTER.format(now);
    }

}
