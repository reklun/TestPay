package processor;

import dataObject.Tap;
import dataObject.TravelRecord;
import util.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is for pairing the result extracted from CSV file
 */
public class PairingProcessor {

    /**
     * pairing the tap on and off records and return a map
     *
     * @param travelRecords
     */
    public static Map<String, Map<Long, List<TravelRecord>>> pairing(List<TravelRecord> travelRecords) {

        // Pairing logic:
        // Define a key for outer map using company id, bus id and pan
        // If it is a ON, put it as a new record
        // If it is another ON sharing the same company id, bus id and pan,
        //      the timestamp in seconds will the key of the inner map

        // If it is a OFF, look for an existing record sharing the generated key, and match to the closest ON time record.
        // This assumes the records come in accordingly by time. If not, the logic needs to be updated.

        Map<String, Map<Long, List<TravelRecord>>> paired = new HashMap<>();

        for (TravelRecord travelRecord : travelRecords) {
            String key = travelRecord.generateKey();

            // if this is tap ON and the first record found, put it in map
            if (travelRecord.getTapType() == Tap.ON) {
                if (paired.get(key) == null) {
                    Map<Long, List<TravelRecord>> pairingInner = new HashMap<>();
                    pairingInner.put(DateTimeUtil.toEpoch(travelRecord.getDateTimeUTC()), new ArrayList<TravelRecord>() {{
                        add(travelRecord);
                    }});

                    paired.put(key, pairingInner);
                } else {
                    // if it is found, it must be a different set of records
                    Map<Long, List<TravelRecord>> pairingInner = paired.get(key);
                    pairingInner.put(DateTimeUtil.toEpoch(travelRecord.getDateTimeUTC()), new ArrayList<TravelRecord>() {{
                        add(travelRecord);
                    }});
                }
            } else {
                // this is a tap OFF so look for a pairing target
                if (paired.get(key) == null) {
                    // TODO this is an error. A tap OFF without a tap ON
                } else {
                    // if it is found, put it to the closest time record
                    Map<Long, List<TravelRecord>> pairingInner = paired.get(key);

                    Long startTime = getClosestTime(pairingInner, DateTimeUtil.toEpoch(travelRecord.getDateTimeUTC()));
                    pairingInner.get(startTime).add(travelRecord);
                }
            }
        }

        return paired;
    }

    /**
     * Return the most recent tap ON time for a OFF
     *
     * @return
     */
    private static Long getClosestTime(Map<Long, List<TravelRecord>> topOnMap, Long finishTime) {
        Long startTime = finishTime;

        // this is used for calculation
        Double checker = 0.0;

        for (Map.Entry<Long, List<TravelRecord>> entry : topOnMap.entrySet()) {
            Long onTime = entry.getKey();
            Double onTimeDouble = onTime.doubleValue();
            Double finishTimeDouble = finishTime.doubleValue();

            if (finishTimeDouble < onTimeDouble) {
                continue;
            }

            // get the closest time
            // as it is a small value, look for the largest inverse
            Double inverseChecker = 1 / (finishTimeDouble - onTimeDouble);

            if (inverseChecker > checker) {
                checker = inverseChecker;
                startTime = onTime;
            }
        }

        return startTime;
    }

}
