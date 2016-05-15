package com.example.abhishek.deliverywala.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.abhishek.deliverywala.activities.MainActivity;
import com.example.abhishek.deliverywala.views.RestaurantView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abhishek on 15-05-2016.
 */
public class DownloadImage {
    public interface Callback {
        public void imageFetched(Bitmap bitmap);
    }
 static Bitmap bt2;

    public static Bitmap downloadBitmap(final Context context, final String url, final Callback callback) {
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connect1 = null;
                try {
                    URL requestUrl = new URL(url);
                    connect1 = (HttpURLConnection) requestUrl.openConnection();
                    connect1.connect();
                    int responseCode = connect1.getResponseCode();
                    if (responseCode == 200) {
                        Bitmap bt = BitmapFactory.decodeStream(connect1.getInputStream());
                        bt2 = bt;
                    }
                } catch (Exception e) {
                } finally {
                    if (connect1 != null) {
                        connect1.disconnect();
                    }
                }
            }

        });
        thread2.start();
    return bt2;}
}
