package util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * return the current time in string
     *
     * @return
     */
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return DATE_TIME_FORMATTER.format(now);
    }

    /**
     * change string into local date time object
     *
     * @return
     */
    public static LocalDateTime parseDateTimeStr(String strDateTime) {
        return LocalDateTime.parse(strDateTime, DATE_TIME_FORMATTER);
    }

    /**
     * convert LocaoDateTime to Epoch
     *
     * @param localDateTime
     * @return
     */
    public static Long toEpoch(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * return a date time in defined format here
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * calculate the difference between the two local datetime and return a string
     *
     * @param start
     * @param stop
     * @return
     */
    public static String getDifference(LocalDateTime start, LocalDateTime stop) {
        Long diff = toEpoch(stop) - toEpoch(start);
        return diff.toString();
    }

}
