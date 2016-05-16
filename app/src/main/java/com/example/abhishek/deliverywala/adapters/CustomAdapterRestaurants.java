package com.example.abhishek.deliverywala.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
public class CustomAdapterRestaurants extends BaseAdapter implements DownloadImage.Callback
{ int i=0;
        Context context;
        List<RowItem> rowItems;
    ImageView restaurantimg;
    ViewHolder holder = null;

    String restaurant_imgurls[]={"https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/AFC/AFC.jpg",
            "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Bobis/75127_10150115172557519_3761734_n.jpg",
            "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Cinnamon/0.jpg",
            "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Dominos/75127_10150115172557519_3761734_n.jpg",
            "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Tundey+kebabi/75127_10150115172557519_3761734_n.jpg"};


    public CustomAdapterRestaurants(Context context, List<RowItem> items) {
            this.context = context;
            this.rowItems = items;
        }

    @Override
    public void imageFetched(Bitmap bitmap) {

    }

    /*private view holder class*/
        public class ViewHolder {
            ImageView restaurantimg;
            TextView restaurantname;

        }

        public View getView(int position, View convertView, ViewGroup parent) {

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
           // holder.restaurantimg.setImageBitmap(rowItem.getImageId());

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


    public void method() {

        while(restaurant_imgurls[i]!=null) {
            String str=restaurant_imgurls[i].toString();
            DownloadImage.downloadBitmap(context,str,CustomAdapterRestaurants.this,holder.restaurantimg);
            i++;
        }
    }
    }
