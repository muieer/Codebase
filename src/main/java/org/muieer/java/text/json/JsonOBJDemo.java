package org.muieer.java.text.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonOBJDemo {
    public static void main(String[] args) {
        String json = "{\"keywords\":[\"中职\",\"电子电工专业\",\"信息技术\"]}";
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        System.out.println(jsonObject);
    }
}
