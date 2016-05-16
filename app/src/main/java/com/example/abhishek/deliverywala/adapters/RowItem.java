package com.example.abhishek.deliverywala.adapters;

import android.graphics.Bitmap;

/**
 * Created by Abhishek on 15-05-2016.
 */
public class RowItem {
    private String url;
        private String title;

        public RowItem(String url, String title) {
            this.url = url;
            this.title = title;
        }
        public String getUrlId() {
            return url;
        }
        public void setUrlId(String url) {
            this.url = url;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        @Override
        public String toString() {
            return title + "\n";
        }
    }