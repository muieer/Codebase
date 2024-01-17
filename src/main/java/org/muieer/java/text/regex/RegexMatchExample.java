package org.muieer.java.text.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatchExample {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("abc\\d");

        // 整个输入字符串都需要与正则表达式匹配
        Matcher matcher1 = pattern.matcher("abc1");
        if (matcher1.matches()) {
            System.out.println("matcher1.matches() return true, matcher1.group() is " + matcher1.group());
        }

        // 输入字符串匹配的部分必须从索引 0 开始，不要求全部
        Matcher matcher2 = pattern.matcher("abc1a");
        if (matcher2.lookingAt()) {
            System.out.println("matcher2.lookingAt() return true, matcher2.group() is " + matcher2.group());
        }

        // 输入字符串的子字符串与正则表达式匹配就行
        Matcher matcher3 = pattern.matcher("2abc1aabc2");
        while (matcher3.find()) {
            System.out.println("matcher3.find() return true, matcher3.group() is " + matcher3.group());
        }
    }
}
