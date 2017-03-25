package com.luan.movieandroidapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Luan on 3/4/17.
 */

public class ClientServerConnector {
    private String serverURL = "http://ec2-35-162-76-153.us-west-2.compute.amazonaws.com:8080/MovieWebApp";
    private HttpURLConnection urlConnection;

    public JSONObject getJsonObject(String path, Map<String, String> map) {
        JSONObject jsonObject = null;
        try {
            String tempURLString = serverURL + "/" + path + "?";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                tempURLString += entry.getKey() + "=" + entry.getValue() + "&";
            }
            URL url = new URL(tempURLString.substring(0, tempURLString.length() - 1));
            this.urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // get the stream from servlet
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            try {
                jsonObject = new JSONObject(responseStrBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONArray getJsonArray(String path, Map<String, String> map) {
        JSONArray jsonArray = null;
        try {
            String tempURLString = serverURL + "/" + path + "?";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                tempURLString += entry.getKey() + "=" + entry.getValue() + "&";
            }
            URL url = new URL(tempURLString.substring(0, tempURLString.length() - 1));
            this.urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // get the stream from servlet
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            try {
                System.out.println(responseStrBuilder.toString());
                jsonArray = new JSONArray(responseStrBuilder.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
