package com.example.abhishek.deliverywala.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.deliverywala.R;

import java.util.List;

/**
 * Created by Abhishek on 15-05-2016.
 */
public class CustomAdapterRestaurants extends BaseAdapter
{
        Context context;
        List<RowItem> rowItems;

        public CustomAdapterRestaurants(Context context, List<RowItem> items) {
            this.context = context;
            this.rowItems = items;
        }

        /*private view holder class*/
        private class ViewHolder {
            ImageView restaurantimg;
            TextView restaurantname;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.restaurantcard_view, null);
                holder = new ViewHolder();
                holder.restaurantname = (TextView) convertView.findViewById(R.id.restaurant_name);
                holder.restaurantimg = (ImageView) convertView.findViewById(R.id.restaurant_img);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            RowItem rowItem = (RowItem) getItem(position);

            holder.restaurantname.setText(rowItem.getTitle());
            holder.restaurantimg.setImageBitmap(rowItem.getImageId());

            return convertView;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return rowItems.indexOf(getItem(position));
        }
    }
