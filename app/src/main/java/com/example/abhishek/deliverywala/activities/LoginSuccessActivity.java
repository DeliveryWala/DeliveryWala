package com.example.abhishek.deliverywala.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.exceptions.BackendlessFault;
import com.example.abhishek.deliverywala.R;
import com.example.abhishek.deliverywala.callbacks.DefaultCallback;

/**
 * Created by Abhishek on 06-05-2016.
 */
public class LoginSuccessActivity extends AppCompatActivity  implements ActionBar.TabListener {



    private static final int NUM_PAGES =6;
    private ViewPager mPagermenu;
    private PagerAdapter mPagerAdaptermenu;
    private android.support.v7.app.ActionBar actionBarmenu;
    private String[] tabs = {"Restaurant","Bakers","Daily Use","Grocery","Medicines","Fun Alley","Contact Us"};

    private Button logoutButton;

        public void onCreate( Bundle savedInstanceState )
        {
            super.onCreate( savedInstanceState );
            setContentView(R.layout.login_success );
            mPagermenu = (ViewPager) findViewById(R.id.menupager);
            actionBarmenu = getSupportActionBar();
            mPagerAdaptermenu = new ScreenSlidePagerAdapterMenu(getSupportFragmentManager());
            mPagermenu.setAdapter(mPagerAdaptermenu);
            actionBarmenu.setHomeButtonEnabled(false);
            actionBarmenu.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            for (String tab_name : tabs) {
                actionBarmenu.addTab(actionBarmenu.newTab().setText(tab_name)
                        .setTabListener(this));
            }
            mPagermenu.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                               @Override
                                               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                               }

                                               @Override
                                               public void onPageSelected(int position) {

                                                   actionBarmenu.setSelectedNavigationItem(position);
                                               }

                                               @Override
                                               public void onPageScrollStateChanged(int state) {

                                               }
                                           });
                initUI();
        }
    public void onBackPressed() {
        if (mPagermenu.getCurrentItem() == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            //  super.onBackPressed();

        } else {
            mPagermenu.setCurrentItem(0);
        }    }


    private void initUI()
        {
            logoutButton = (Button) findViewById( R.id.logoutButton );

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLogoutButtonClicked();
                }
            });
        }

        private void onLogoutButtonClicked()
        {
            Backendless.UserService.logout( new DefaultCallback<Void>( this )
            {
                @Override
                public void handleResponse( Void response )
                {
                    super.handleResponse(response);
                    Intent intent = new Intent(LoginSuccessActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void handleFault( BackendlessFault fault )
                {
                    if( fault.getCode().equals( "3023" ) ) // Unable to logout: not logged in (session expired, etc.)
                        handleResponse( null );
                    else
                        super.handleFault( fault );
                }
            } );

        }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mPagermenu.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    class ScreenSlidePagerAdapterMenu extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapterMenu (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePagerActivityMenu screenSlidePagerActivity = new ScreenSlidePagerActivityMenu(LoginSuccessActivity.this);
            screenSlidePagerActivity.setPosition(position);
            return screenSlidePagerActivity;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}