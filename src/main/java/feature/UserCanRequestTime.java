package feature;

import http.RestHelper;
import model.time.Data;
import model.time.TimeResponseModel;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserCanRequestTime {
    private final static String DATE_PATTERN = "EEEE, MMMM dd, yyyy h:mm:ss a XXX";

    public static TimeResponseModel requestServerCurrentTime() {
        return RestHelper.getTime();
    }

    public static Data getLocalTime() {
        ZonedDateTime now = ZonedDateTime.now();
        String dateByPattern = getDateByPattern(now);

        return Data.builder()
                .timeLocal(dateByPattern)
                .timestamp(now.toEpochSecond())
                .timestampGmt(now.toEpochSecond())
                .timestampLocal(now.toEpochSecond() + now.getOffset().getTotalSeconds())
                .timezone(now.getZone().toString())
                .utcOffset(now.getOffset().getTotalSeconds())
                .build();
    }

    private static String getDateByPattern(ZonedDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return time.format(formatter);
    }
}
