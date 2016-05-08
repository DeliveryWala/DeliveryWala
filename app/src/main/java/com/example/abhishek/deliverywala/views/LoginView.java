package com.example.abhishek.deliverywala.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.abhishek.deliverywala.R;
import com.example.abhishek.deliverywala.activities.LoginSuccessActivity;
import com.example.abhishek.deliverywala.callbacks.DefaultCallback;
import com.example.abhishek.deliverywala.utils.Defaults;

/**
 * Created by Abhishek on 07-05-2016.
 */
public class LoginView extends BaseView{
    /**
     * Created by Abhishek on 13-02-2016.
     */

        private EditText emaillogin, passwordlogin;
        private Button loginButton;
        private CheckBox rememberLoginBox;
        public LoginView(Context context) {

            super(context);
            //LayoutInflater  mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //mInflater.inflate(R.layout.logindetails, this, true);
       /* mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.logindetails, (ViewGroup) getRootView(),false);*/
            //method();

        }

        @Override
        protected View getView(int layOutId) {
            return super.getView(layOutId);
        }

        public void method()
        {
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
                                    Intent intent = new Intent(mContext, LoginSuccessActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    mContext.startActivity(intent);

                                }
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
                mView = mInflater.inflate(R.layout.logindetails, parent, false);
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
            emaillogin = (EditText) mView.findViewById(R.id.emaillogin);
            passwordlogin = (EditText) mView.findViewById(R.id.passwordlogin);
            loginButton = (Button) mView.findViewById(R.id.loginButton);
            rememberLoginBox = (CheckBox) mView.findViewById(R.id.rememberLoginBox);
            loginButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLoginButtonClicked();
                }
            });


        }
        public void onLoginButtonClicked() {
            String identity = emaillogin.getText().toString();
            String password = passwordlogin.getText().toString();
            boolean rememberLogin = rememberLoginBox.isChecked();

            Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(mContext) {
                public void handleResponse(BackendlessUser backendlessUser) {
                    super.handleResponse(backendlessUser);
                    mContext.startActivity(new Intent(mContext, LoginSuccessActivity.class));

                }
            }, rememberLogin);
        }






}
