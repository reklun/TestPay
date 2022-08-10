package processor;

import dataObject.ResultRecord;
import dataObject.StopID;
import dataObject.TravelRecord;
import dataObject.TravelStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pricing.PricingTable;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dataObject.Tap.*;
import static dataObject.TravelStatus.CANCELLED;
import static dataObject.TravelStatus.COMPLETED;
import static dataObject.TravelStatus.INCOMPLETE;
import static util.Constant.DOLLAR;
import static util.Constant.UNDEFINED;

/**
 * preparing list of strings for print to write
 */
public class ResultProcessor {

    private static final Logger LOGGER = LogManager.getLogger(ResultProcessor.class);

    public static List<ResultRecord> buildResult(Map<String, Map<Long, List<TravelRecord>>> pairedMap) {
        List<ResultRecord> results = new ArrayList<>();

        // looping the generated ID, it is unique except the timestamp
        for (Map<Long, List<TravelRecord>> innerMap : pairedMap.values()) {

            // this has a timestamp to be the secondary key for each paired of records
            for (List<TravelRecord> pairedList : innerMap.values()) {
                if (pairedList != null && !pairedList.isEmpty()) {
                    if (pairedList.size() == 1) {
                        results.add(buildIncompleteTrip(pairedList.get(0), INCOMPLETE));

                    } else if (pairedList.size() == 2) {
                        results.add(buildCompleteTrip(pairedList.get(0), pairedList.get(1), COMPLETED));

                    } else {
                        // This is not right. Need to raise in logs
                        LOGGER.error("This is not paired correctly.");
                    }

                }
            }
        }

        return results;
    }

    /**
     * for building a record of complete trip
     *
     * @param start
     * @param stop
     * @return
     */
    protected static ResultRecord buildCompleteTrip(TravelRecord start, TravelRecord stop, TravelStatus status) {

        ResultRecord result = new ResultRecord();

        LocalDateTime startTime = start.getDateTimeUTC();
        LocalDateTime stopTime = stop.getDateTimeUTC();

        result.setStarted(DateTimeUtil.localDateTimeToString(start.getDateTimeUTC()));
        // this is a case for incomplete trip
        if (stopTime == null) {
            result.setFinished(UNDEFINED);
            result.setDurationSecs(UNDEFINED);
        } else {
            result.setFinished(DateTimeUtil.localDateTimeToString(stop.getDateTimeUTC()));
            result.setDurationSecs(DateTimeUtil.getDifference(startTime, stopTime));
        }

        StopID startStop = start.getStopId();
        StopID stopStop = stop.getStopId();
        result.setFromStopId(startStop.toString());
        result.setToStopId(stopStop.toString());
        if (startStop == stopStop) {
            // a special case where the start stop is the end stop
            status = CANCELLED;
        }

        // adding a dollar sign in front
        Double fee = PricingTable.getFee(start.getStopId(), stop.getStopId());
        result.setChargeAmount(DOLLAR + fee);

        result.setCompanyId(start.getCompanyId());

        // BusID becomes BID
        result.setBusID(start.getBusID().substring(0, 1) + start.getBusID().substring(3));

        result.setPAN(start.getPan());

        result.setStatus(status.name());

        return result;
    }

    /**
     * for building a record of incomplete trip
     *
     * @param start
     * @return
     */
    protected static ResultRecord buildIncompleteTrip(TravelRecord start, TravelStatus status) {

        // build the OFF record for incomplete trip
        TravelRecord stop = new TravelRecord();
        stop.setId(0); // depending on requirement
        stop.setDateTimeUTC(null);
        stop.setTapType(OFF);
        stop.setStopId(PricingTable.findLongestRoute(start.getStopId())); // this is based on pricing calculation
        stop.setCompanyId(start.getCompanyId());
        stop.setBusID(start.getBusID());
        stop.setPan(start.getPan());

        return buildCompleteTrip(start, stop, status);
    }

}
