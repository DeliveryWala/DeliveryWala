package com.example.abhishek.deliverywala.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abhishek.deliverywala.R;
import com.example.abhishek.deliverywala.adapters.CustomAdapterRestaurants;
import com.example.abhishek.deliverywala.adapters.DownloadImage;
import com.example.abhishek.deliverywala.adapters.RowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 10-05-2016.
 */
public class RestaurantView extends BaseView implements AdapterView.OnItemClickListener,DownloadImage.Callback {
    @Override
    protected View getView(int layOutId) {
        return super.getView(layOutId);
    }
    String restaurant_imgurls[];
    public static final String[] titles = new String[] { "AFC",
            "Bobi's", "Cinnamon", "Dominos", "Tunday Kebabi"};
    ListView listView;
    List<RowItem> rowItems;
    Bitmap bt[];
    int i=0;
    @Override
    public View getPopulatedView(View mView, ViewGroup parent) {
        if (mView == null) {
            method();
            mView = mInflater.inflate(R.layout.restaurant_view1, parent, false);
            rowItems = new ArrayList<RowItem>();
            for (int i = 0; i < titles.length; i++) {
                RowItem item = new RowItem(bt[i], titles[i]);
                rowItems.add(item);
            }

            listView=(ListView)findViewById(R.id.listView);
            CustomAdapterRestaurants adapter = new CustomAdapterRestaurants(getCurrentContext(), rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
        this.mView = mView;
        return super.getPopulatedView(mView, parent);
    }

    public void method() {

       String restaurant_imgurls[]={"https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/AFC/AFC.jpg",
                "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Bobis/75127_10150115172557519_3761734_n.jpg",
                "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Cinnamon/0.jpg",
                "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Dominos/75127_10150115172557519_3761734_n.jpg",
                "https://api.backendless.com/0BD3EAA1-CE64-2E65-FF67-ACE258EABD00/v1/files/restaurant_images/Tundey+kebabi/75127_10150115172557519_3761734_n.jpg"};
       while(restaurant_imgurls[i]!=null) {
           String str=restaurant_imgurls[i].toString();
          bt[i]= DownloadImage.downloadBitmap(getCurrentContext(),str,RestaurantView.this);
       i++;
       }
    }

        private void initUI() {

    }
    @Override
    protected Context getCurrentContext() {
        return super.getCurrentContext();
    }

    @Override
    public View getPopulatedView(View mView, ViewGroup parent, boolean showHeaderText) {
        return super.getPopulatedView(mView, parent, showHeaderText);
    }

    @Override
    public View getPopulatedView(View mView, ViewGroup parent, boolean showHeaderText, Boolean isScrolling) {
        return super.getPopulatedView(mView, parent, showHeaderText, isScrolling);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public RestaurantView(Context context) {
        super(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(getContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    @Override
    public void imageFetched(Bitmap bitmap) {

    }
}
