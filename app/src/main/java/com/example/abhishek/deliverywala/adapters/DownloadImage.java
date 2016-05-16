package com.example.abhishek.deliverywala.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
 static ImageView img1;
    public class ViewHolder {
        ImageView restaurantimg;
        TextView restaurantname;

    }


    public static void downloadBitmap(final Context context, final String url, final Callback callback,final ImageView img) {

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
                        final Bitmap bt2 = bt;
                        if (callback != null) {

                            ((MainActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    img.setImageBitmap(bt2);
                                    callback.imageFetched(bt2);
                                            }
                            });
                        } else {
                        }
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
    }
}
