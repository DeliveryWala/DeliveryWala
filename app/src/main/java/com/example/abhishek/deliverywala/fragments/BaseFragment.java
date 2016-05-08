package com.example.abhishek.deliverywala.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by Abhishek on 07-05-2016.
 */
    abstract public class BaseFragment extends Fragment {
    /*
        protected Context mContext;
        protected int mFragmentTaskId = -1;
        protected View mView;
        protected Resources mResources;
        private View rootView = null;
        protected LayoutInflater layoutInflater;
        protected View containerView;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mFragmentTaskId = hashCode();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_youth, container, false);
            mContext = getActivity();
            mResources = mContext.getResources();
            layoutInflater = LayoutInflater.from(getActivity());
            return rootView;
        }

        protected View setContentView(int layoutId, View parentView) {
            layoutInflater = LayoutInflater.from(getActivity().getBaseContext());
            LinearLayout containerLayout = (LinearLayout) parentView.findViewById(R.id.containerLayout);
            View view = layoutInflater.inflate(layoutId, containerLayout);
            return view;
        }

        @Override
        public void onResume() {
            if( ! (this instanceof YStoriesFragment || this instanceof  JobsFragment || this instanceof DiscussionsFragment)) {
                if(mContext instanceof BaseActivity)
                    ((BaseActivity) mContext).setCurrentFragment(this);
            }
            setActionBar();

            GoogleAnalyticsManager.getInstance().setGoogleAnalyticsScreenName(this.getClass().getSimpleName());

            super.onResume();
        }

        public void setActionBar(){
            if(mContext instanceof YouthActivity) {
                ((YouthActivity) mContext).getSupportActionBar().show();
                ((YouthActivity) mContext).setDefaultActionBar(null);
            }
        };*/
    }

