package com.example.abhishek.deliverywala.adapters;

import android.graphics.Bitmap;

/**
 * Created by Abhishek on 15-05-2016.
 */
public class RowItem {
    private Bitmap bt;
        private String title;

        public RowItem(Bitmap bt, String title) {
            this.bt = bt;
            this.title = title;
        }
        public Bitmap getImageId() {
            return bt;
        }
        public void setImageId(Bitmap bt) {
            this.bt = bt;
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