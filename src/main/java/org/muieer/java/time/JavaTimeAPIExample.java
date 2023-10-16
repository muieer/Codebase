package org.muieer.java.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

/*
* JSR 310: https://jcp.org/en/jsr/detail?id=310
* doc: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/package-summary.html
* */
public class JavaTimeAPIExample {

    public static void main(String[] args) {
        System.out.println("本机系统所属时区是:" + ZoneId.systemDefault());
        System.out.println("timeStampToLocalDateTime:" + epochMilliToLocalDateTime(1695342267442L));
        System.out.println("epochMilliToDayOfWeek:" + epochMilliToDayOfWeek(1695342267442L));
        // 当前时间的前三天
        System.out.println(epochMilliToLocalDateTime(Clock.systemDefaultZone().millis()).minusDays(3L));
        System.out.println(localDateTimeToTimeStamp(epochMilliToLocalDateTime(1695342267442L)));
        System.out.println(localDateTimeToString(epochMilliToLocalDateTime(1695342267442L), DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDateTimeToString(epochMilliToLocalDateTime(1695342267442L), DateTimeFormatter.ofPattern("yyyyMMddHH")));
        System.out.println(textToLocalDateTime("20230101 12:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")));
        System.out.println(localDateTimeToDate(textToLocalDateTime("20230101 12:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"))));
        System.out.println(localDateTimeToDate(LocalDateTime.now()));
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime textToLocalDateTime(String text, DateTimeFormatter formatter) {
        return LocalDateTime.parse(text, formatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return formatter.format(localDateTime);
    }

    public static long localDateTimeToTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static int epochMilliToDayOfWeek(long epochMilli) {
        return epochMilliToLocalDateTime(epochMilli).getDayOfWeek().getValue();
    }

    public static LocalDateTime epochMilliToLocalDateTime(long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

}
