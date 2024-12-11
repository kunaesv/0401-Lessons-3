package ru.demo;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    public static String TranslateToRU(String text) throws Exception {
        final String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";

        try {
            String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            //System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
        }
        catch(IOException | org.json.JSONException e){
            e.printStackTrace();
            return ("Ошибка: " + e.getMessage());
        }
    }
    public static String TranslatorToEng(String text) throws Exception {
        final String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=ru&tl=en&dt=t&q=";

        try {
            String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");

            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            //System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
        }
        catch(IOException | org.json.JSONException e){
            e.printStackTrace();
            return ("Ошибка: " + e.getMessage());
        }
    }
}
