package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    URL url = new URL(BASE_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    System.out.println(responseCode);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    JSONObject jsonResponse = new JSONObject(response.toString());

                    if (jsonResponse.has("message")) {
                        System.out.println(jsonResponse.getString("message"));
                        return;
                    }

                } catch (IOException | org.json.JSONException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            future.get();

        System.out.println("Программа завершена.");
    }
}