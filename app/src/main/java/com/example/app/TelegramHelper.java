package com.example.app;

import android.util.Log;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TelegramHelper {

    // Token bot kamu
    private static final String BOT_TOKEN = "8948553402:AAEtUVHdhpfET5QU6r4HScXwyb5CngSYoqk";

    public static void sendMessage(String chatId, String text) {
        new Thread(() -> {
            try {
                String urlString = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                String data = "chat_id=" + URLEncoder.encode(chatId, "UTF-8")
                            + "&text=" + URLEncoder.encode(text, "UTF-8")
                            + "&parse_mode=" + URLEncoder.encode("Markdown", "UTF-8");

                byte[] out = data.getBytes(StandardCharsets.UTF_8);

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(out);
                }

                int responseCode = conn.getResponseCode();
                Log.d("TelegramHelper", "Response Code: " + responseCode);

                conn.disconnect();
            } catch (Exception e) {
                Log.e("TelegramHelper", "Error sending message to Telegram", e);
            }
        }).start();
    }
}
