package com.example.app;

import android.util.Log;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiHelper {

    // URL Web App GAS kamu
    private static final String GAS_URL = "https://script.google.com/macros/s/AKfycbxzwRGDR72R9DEk0n12E0kjuCRXfrBVDTC5uob6RGck2GNinzI2mp4wDj6nIckLTp0/exec";

    public static void sendNotificationToSheet(String packageName, String title, String messageText) {
        new Thread(() -> {
            try {
                URL url = new URL(GAS_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("packageName", packageName);
                jsonParam.put("title", title);
                jsonParam.put("message", messageText);

                byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                Log.d("ApiHelper", "Response Code: " + responseCode);

                conn.disconnect();
            } catch (Exception e) {
                Log.e("ApiHelper", "Error sending data to GAS", e);
            }
        }).start();
    }
}
