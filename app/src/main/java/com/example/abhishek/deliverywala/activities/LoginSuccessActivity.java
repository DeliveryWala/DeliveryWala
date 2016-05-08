package com.example.abhishek.deliverywala.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.exceptions.BackendlessFault;
import com.example.abhishek.deliverywala.R;
import com.example.abhishek.deliverywala.callbacks.DefaultCallback;

/**
 * Created by Abhishek on 06-05-2016.
 */
public class LoginSuccessActivity extends Activity {




        private Button logoutButton;

        public void onCreate( Bundle savedInstanceState )
        {
            super.onCreate( savedInstanceState );
            setContentView(R.layout.login_success );


            initUI();
        }

        private void initUI()
        {
            logoutButton = (Button) findViewById( R.id.logoutButton );

            logoutButton.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View view )
                {
                    onLogoutButtonClicked();
                }
            } );
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
    }
