package com.example.app;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // Dipanggil saat notifikasi baru masuk
        String packageName = sbn.getPackageName();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Dipanggil saat notifikasi dihapus
    }
}
