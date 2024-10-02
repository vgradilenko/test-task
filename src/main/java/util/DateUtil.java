package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public final static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public final static String DATE_PATTERN_READ = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX";

    public static String convertDateToString(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime convertToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_READ);
        return ZonedDateTime.parse(date, formatter);
    }
}
