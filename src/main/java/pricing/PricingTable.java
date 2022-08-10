package pricing;

import dataObject.StopID;

import static dataObject.StopID.STOP1;
import static dataObject.StopID.STOP2;
import static dataObject.StopID.STOP3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * for denoting the fee for different stops
 */
public class PricingTable {

    // initializing the table with Set as keys
    public static final Map<Set<StopID>, Double> FEE_MAP = new HashMap<>();

    static {
        FEE_MAP.put(new HashSet<StopID>() {{
            add(STOP1);
            add(STOP2);
        }}, 3.25);
        FEE_MAP.put(new HashSet<StopID>() {{
            add(STOP2);
            add(STOP3);
        }}, 5.50);
        FEE_MAP.put(new HashSet<StopID>() {{
            add(STOP1);
            add(STOP3);
        }}, 7.30);
    }

    /**
     * return the fee based on the stops
     *
     * @param start
     * @param stop
     * @return
     */
    public static Double getFee(StopID start, StopID stop) {
        // if the initial stop is the last stop, no charge
        if (start == stop) {
            return 0.0;
        }

        // return fee based on the stops
        Set<StopID> key = new HashSet<StopID>() {{
            add(start);
            add(stop);
        }};
        return FEE_MAP.get(key);
    }

    /**
     * for any given starting stop, return the ending stop with highest price
     *
     * @return
     */
    public static StopID findLongestRoute(StopID startStop) {

        StopID endStop = startStop;
        Double fee = 0.0;

        for (Map.Entry<Set<StopID>, Double> entry : FEE_MAP.entrySet()) {
            // for each item on price table
            // get the combinations of stops and corresponding fee
            Set<StopID> stops = new HashSet<>(entry.getKey());
            Double feeForThisTrip = entry.getValue();

            // if a stop is in the set, store the stopId and fee
            if (stops.contains(startStop) && feeForThisTrip > fee) {
                // only two stops in each item. pop the start and get the remanining one
                stops.remove(startStop);
                endStop = stops.iterator().next();

                fee = feeForThisTrip;
            }
        }

        return endStop;
    }

}
