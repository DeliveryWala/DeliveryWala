package com.example.abhishek.deliverywala.activities;

/**
 * Created by Abhishek on 06-05-2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.deliverywala.views.LoginView;
import com.example.abhishek.deliverywala.views.RegisterView;


public class ScreenSlidePagerActivity extends android.support.v4.app.Fragment {
    int pos;
    Context context;

    public ScreenSlidePagerActivity(Context context) {
        this.context=context;
    }

    public void setPosition(int position) {
        this.pos = position;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if(pos==0) {
            view = new LoginView(context).getPopulatedView(null,container);
        }
        else if(pos==1)
        {
            view =  new RegisterView(context).getPopulatedView(null,container);
        }
        else
        {
            view =  new LoginView(context).getPopulatedView(null, container);
        }
        return view;
    }
}
