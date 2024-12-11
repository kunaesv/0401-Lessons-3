package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String BASE_URL = "https://official-joke-api.appspot.com/jokes/programming/random";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    URL url = new URL(BASE_URL);
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

                    JSONArray jsonResponseArray = new JSONArray(response.toString());
                    JSONObject jsonResponse = jsonResponseArray.getJSONObject(0);
                    //System.out.println(response);
                    if (jsonResponse.has("message")) {
                        System.out.println(jsonResponse.getString("message"));
                        return;
                    }

                    System.out.println(jsonResponse.getString("setup") + " "+ jsonResponse.getString("punchline") + "\n");
//                    System.out.println(jsonResponse.getString("punchline"));
                    String text = jsonResponse.getString("setup") + jsonResponse.getString("punchline");
                    System.out.println(Translator(text));

                } catch (IOException | org.json.JSONException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                    e.printStackTrace();
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            future.get();

        System.out.println("Программа завершена.");
    }

    private static String Translator(String text) throws Exception {
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
}

