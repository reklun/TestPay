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

    // Or concat the stop strings as keys
//    static {
//        FEE_MAP.put("Stop1Stop2", 3.25);
//        FEE_MAP.put("Stop2Stop1", 3.25);
//        FEE_MAP.put("Stop2Stop3", 5.50);
//        FEE_MAP.put("Stop3Stop2", 5.50);
//        FEE_MAP.put("Stop1Stop3", 7.30);
//        FEE_MAP.put("Stop3Stop1", 7.30);
//    };

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

}
