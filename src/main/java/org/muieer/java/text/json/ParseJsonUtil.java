package org.muieer.java.text.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ParseJsonUtil {

    private static final Gson gson = new Gson();

    private ParseJsonUtil() {}

    public static void main(String[] args) throws Exception {

        FileSystem fileSystem = FileSystem.get(new Configuration());
        String text = IOUtils.toString(fileSystem.open(new Path(args[0])), StandardCharsets.UTF_8);

        Map<String, Object> map = parseJson(text, new TypeToken<Map<String, Object>>() {}.getType());
        System.out.println(map.keySet());

    }

    public static <T> T parseJson(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }

    public static <T> T parseJson(String text, Type type) {
        return gson.fromJson(text, type);
    }



}
