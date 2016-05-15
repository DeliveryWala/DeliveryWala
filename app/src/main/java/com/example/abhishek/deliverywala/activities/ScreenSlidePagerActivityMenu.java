package com.example.abhishek.deliverywala.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.deliverywala.views.BakersView;
import com.example.abhishek.deliverywala.views.ContactUsView;
import com.example.abhishek.deliverywala.views.DailyUseView;
import com.example.abhishek.deliverywala.views.FunAlleyView;
import com.example.abhishek.deliverywala.views.GroceryView;
import com.example.abhishek.deliverywala.views.LoginView;
import com.example.abhishek.deliverywala.views.MedicinesView;
import com.example.abhishek.deliverywala.views.RegisterView;
import com.example.abhishek.deliverywala.views.RestaurantView;

/**
 * Created by Abhishek on 10-05-2016.
 */
public class ScreenSlidePagerActivityMenu extends android.support.v4.app.Fragment {
    int pos;
    Context mcontext;

    public ScreenSlidePagerActivityMenu(Context context) {
        this.mcontext=context;
    }

    public void setPosition(int position) {
        this.pos = position;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if(pos==0) {
            view = new RestaurantView(mcontext).getPopulatedView(null,container);
        }
        else if(pos==1)
        {
            view =  new BakersView(mcontext).getPopulatedView(null,container);
        }
        else if(pos==2)
        {
            view =  new DailyUseView(mcontext).getPopulatedView(null,container);
        }

        else if(pos==3)
        {
            view =  new GroceryView(mcontext).getPopulatedView(null,container);
        }

        else if(pos==4)
        {
            view =  new MedicinesView(mcontext).getPopulatedView(null,container);
        }
        else if(pos==5)
        {
            view =  new FunAlleyView(mcontext).getPopulatedView(null,container);
        }
        else if(pos==6)
        {
            view =  new ContactUsView(mcontext).getPopulatedView(null,container);
        }
        else
        {
            view =  new RestaurantView(mcontext).getPopulatedView(null, container);
        }
        return view;
    }
}