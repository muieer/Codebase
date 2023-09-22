package org.muieer.java.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

/*
* JSR 310: https://jcp.org/en/jsr/detail?id=310
* doc: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/package-summary.html
* */
public class JavaTimeAPIExample {

    public static void main(String[] args) {
        System.out.println(YearMonth.now());
        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
    }
}
