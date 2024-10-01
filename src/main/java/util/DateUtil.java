package util;

import config.ConfigProvider;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String convertDateToString(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConfigProvider.CONFIG_PROPS.getDateFormat());
        return zonedDateTime.format(formatter);
    }
}
