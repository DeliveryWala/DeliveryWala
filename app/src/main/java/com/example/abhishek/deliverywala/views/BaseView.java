package com.example.abhishek.deliverywala.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Abhishek on 07-05-2016.
 */
public class BaseView extends LinearLayout implements View.OnClickListener{


        protected Context mContext = null;
        protected LayoutInflater mInflater = null;
        protected View mView = null;
        protected int mLayoutId = -1;
        protected LinearLayout llParentItemView;
        private String mHeader = "";
        private LinearLayout mEmptyView = null;
        private LayoutParams mLayoutParams = null;
        private View mBaseView;


        public BaseView(Context context) {
            super(context);
            mContext = context;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mEmptyView = new LinearLayout(mContext);
            mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mEmptyView.setLayoutParams(mLayoutParams);
        }
        protected View getView(int layOutId) {
            mView = mInflater.inflate(layOutId, mEmptyView, false);
            return mView;
        }
        public View getPopulatedView(View mView,  ViewGroup parent) {
            return mView;
        }

        protected Context getCurrentContext() {
            return mContext;
        }

        public View getPopulatedView(View mView,  ViewGroup parent, boolean showHeaderText) {
            return mView;
        }

        public View getPopulatedView(View mView, ViewGroup parent, boolean showHeaderText, Boolean isScrolling) {
            return mView;
        }
        @Override
        public void onClick(View v) {

        }
    }


