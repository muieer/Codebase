package org.muieer.java.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpUtilForJdk11 {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final Gson gson = new Gson();

    private HttpUtilForJdk11() {}

    public static <T> T sendGetRequest(String url, Map<String, String> params, Class<T> clazz) throws HttpException {
        return sendGetRequest(url, params, clazz, null);
    }

    public static <T> T sendGetRequest(String url, Map<String, String> params, Type type) {
        return sendGetRequest(url, params, null, type);
    }

    private static <T> T sendGetRequest(String url, Map<String, String> params, Class<T> clazz, Type type) {
        try {
            if (params != null && !params.isEmpty()) {
                url += "?" + buildQueryString(params);
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    responseBuilder.append(line);
                }
                in.close();
                return gson.fromJson(responseBuilder.toString(), clazz != null ? clazz : type);
            } else {
                throw new HttpException(responseCode, connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new HttpException("@web, Failed to send GET request to " + url, e);
        } catch (JsonSyntaxException e) {
            throw new HttpException("@web, Failed to parse JSON response from " + url, e);
        }
    }

    public static <T> T sendPostRequest(String url, Object requestBody, Map<String, String> params, Class<T> clazz) throws HttpException {
        return sendPostRequest(url, requestBody, params, clazz, null);
    }

    public static <T> T sendPostRequest(String url, Object requestBody, Map<String, String> params, Type type) throws HttpException {
        return sendPostRequest(url, requestBody, params, null, type);
    }

    private static <T> T sendPostRequest(String url, Object requestBody, Map<String, String> params, Class<T> clazz, Type type) throws HttpException {
        try {
            if (params != null && !params.isEmpty()) {
                url += "?" + buildQueryString(params);
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(POST_METHOD);
            connection.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            connection.setDoOutput(true);

            String requestBodyJson = gson.toJson(requestBody);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBodyJson.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    responseBuilder.append(line);
                }
                in.close();
                return gson.fromJson(responseBuilder.toString(), clazz != null ? clazz : type);
            } else {
                throw new HttpException(responseCode, connection.getResponseMessage());
            }
        } catch (IOException e) {
            throw new HttpException("@web, Failed to send POST request to " + url, e);
        } catch (JsonSyntaxException e) {
            throw new HttpException("@web, Failed to parse JSON response from " + url, e);
        }
    }

    private static String buildQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    public static class HttpException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        private final int statusCode;

        public HttpException(int statusCode, String message) {
            super("HTTP " + statusCode + " " + message);
            this.statusCode = statusCode;
        }

        public HttpException(String message, Throwable cause) {
            super(message, cause);
            this.statusCode = -1;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

    public static void main(String[] args) {
    }
}