package org.muieer.java.shell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParseCommandLineArgumentUtil {

    public static void main(String[] args) {

//        String originCommandLineArgument = StringUtils.join(args, " ");
//        System.out.println(commandLineArgumentToFlinkConfiguration(originCommandLineArgument));
        System.out.println(commandLineArgumentToMap("-cmd1 arg1 -cmd2 arg2 -cmd3 -cmd4"));

    }

    // TODO https://commons.apache.org/proper/commons-cli/index.html，命令行参数解析功能

    /*
    * 命令行解析做的不完善，需要严格遵守输入规范，否则会出错
    * 输入范例：-cmd1 arg1 -cmd2 arg2 -cmd3 -cmd4
    * 规则：cmd以-开头,cmd 与 arg 或 cmd 空格分割
    * */
    public static Map<String, String> commandLineArgumentToMap(String originCommandLineArgument) {
        if (!originCommandLineArgument.startsWith("-")) {
            throw new IllegalArgumentException("originCommandLineArgument should start with -");
        }
        String[] kvArray = originCommandLineArgument.substring(1).split("\\s-");
        Map<String, String> kvMap = new HashMap<>();
        Arrays.stream(kvArray).forEach(kv -> {
            String[] keyAndValue = kv.split("\\s");
            if (keyAndValue.length == 1) {
                kvMap.put(keyAndValue[0], "no_arg");
            } else if (keyAndValue.length == 2) {
                kvMap.put(keyAndValue[0], keyAndValue[1]);
            } else {
                throw new IllegalArgumentException("CommandLine Argument is invalid: " + kv);
            }
        });
        return kvMap;
    }
}
