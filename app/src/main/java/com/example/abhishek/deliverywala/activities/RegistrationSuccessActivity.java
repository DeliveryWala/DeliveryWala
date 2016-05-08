package com.example.abhishek.deliverywala.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abhishek.deliverywala.R;

/**
 * Created by Abhishek on 06-05-2016.
 */
public class RegistrationSuccessActivity  extends Activity {

        private TextView messageView;
        private Button loginButton;

        @Override
        public void onCreate( Bundle savedInstanceState )
        {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.registration_success );

            initUI();
        }

        private void initUI()
        {
            messageView = (TextView) findViewById( R.id.messageView );
            loginButton = (Button) findViewById( R.id.loginButton );


            Resources resources = getResources();
            String message = String.format( resources.getString( R.string.registration_success_message ), resources.getString( R.string.app_name ) );
            messageView.setText( message );

            loginButton.setOnClickListener( new View.OnClickListener()
            {
                @Override
                public void onClick( View view )
                {
                    onLoginButtonClicked();
                }
            } );
        }

        public void onLoginButtonClicked()
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
