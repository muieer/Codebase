package org.muieer.java.text;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsDemo {

    public static void main(String[] args) {
        apacheCommonsLangStringUtils();
    }

    private static void apacheCommonsLangStringUtils() {

        // 数值判断
        System.out.println(StringUtils.isNumeric("12"));
    }
}
