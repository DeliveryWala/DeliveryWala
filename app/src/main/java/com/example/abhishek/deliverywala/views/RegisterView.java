package com.example.abhishek.deliverywala.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.abhishek.deliverywala.R;
import com.example.abhishek.deliverywala.activities.DeliveryWala;
import com.example.abhishek.deliverywala.activities.RegistrationSuccessActivity;
import com.example.abhishek.deliverywala.callbacks.DefaultCallback;
import com.example.abhishek.deliverywala.utils.Defaults;
import com.example.abhishek.deliverywala.utils.DeliverywalaUser;

/**
 * Created by Abhishek on 07-05-2016.
 */
public class RegisterView extends BaseView {

    private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat( "yyyy/MM/dd" );

        private EditText emailField;
        private EditText nameField;
        private EditText phone;
        private EditText passwordField;

        private Button registerButton;

        private String email;
        private String name;
        private String password;

        private DeliverywalaUser user;
        public RegisterView(Context context) {
            super(context);
        }

        @Override
        protected View getView(int layOutId) {
            return super.getView(layOutId);
        }

        public void method() {
            initUI();

            Backendless.setUrl(Defaults.SERVER_URL);
            Backendless.initApp(mContext, Defaults.APPLICATION_ID, Defaults.SECRET_KEY, Defaults.VERSION);


            Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(mContext) {
                @Override
                public void handleResponse(Boolean isValidLogin) {
                    if (isValidLogin && Backendless.UserService.CurrentUser() == null) {
                        String currentUserId = Backendless.UserService.loggedInUser();

                        if (!currentUserId.equals("")) {
                            Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(mContext, "Logging in...") {
                                @Override
                                public void handleResponse(BackendlessUser currentUser) {
                                    super.handleResponse(currentUser);
                                    Backendless.UserService.setCurrentUser(currentUser);
                                    Intent intent = new Intent(mContext, RegistrationSuccessActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    mContext.startActivity(intent);                        }
                            });
                        }
                    }

                    super.handleResponse(isValidLogin);
                }
            });
        }

        @Override
        public View getPopulatedView(View mView, ViewGroup parent) {
            if (mView == null) {
                mView = mInflater.inflate(R.layout.signupdetails, parent, false);
            }
            this.mView = mView;
            method();
            return super.getPopulatedView(mView, parent);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
        }


        private void initUI() {
            emailField = (EditText) mView.findViewById(R.id.emailField);
            nameField = (EditText) mView.findViewById(R.id.namefield);
            passwordField = (EditText) mView.findViewById(R.id.passwordField);
            phone=(EditText) mView.findViewById(R.id.phone);
            registerButton = (Button) mView.findViewById(R.id.signupButton);

            registerButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRegisterButtonClicked();
                }
            } );
        }

        private void onRegisterButtonClicked()
        {
            String emailText = emailField.getText().toString().trim();
            String nameText = nameField.getText().toString().trim();
            String passwordText = passwordField.getText().toString().trim();

            if ( emailText.isEmpty() )
            {
                showToast( "Field 'email' cannot be empty." );
                return;
            }

            if ( passwordText.isEmpty() )
            {
                showToast( "Field 'password' cannot be empty." );
                return;
            }

            if( !emailText.isEmpty() )
            {
                email = emailText;
            }

            if( !nameText.isEmpty() )
            {
                name = nameText;
            }

            if( !passwordText.isEmpty() )
            {
                password = passwordText;
            }

            user = new DeliverywalaUser();

            if( email != null )
            {
                user.setEmail(email);
            }

            if( name != null )
            {
                user.setName( name );
            }

            if( password != null )
            {
                user.setPassword( password );
            }

            Backendless.UserService.register(user, new DefaultCallback<BackendlessUser>(mContext) {
                @Override
                public void handleResponse(BackendlessUser response) {
                    super.handleResponse(response);
                }
            });
        }

        private void showToast( String msg )
        {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }




    }

