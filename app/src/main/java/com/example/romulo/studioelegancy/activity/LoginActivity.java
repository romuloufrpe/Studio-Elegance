package com.example.romulo.studioelegancy.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.romulo.studioelegancy.R;
import com.example.romulo.studioelegancy.User;
import com.example.romulo.studioelegancy.domain.util.LibraryClass;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by romulo on 27/07/16.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SINGUP = 0;

    private Firebase firebase;
    private User user;
    private TextView name;
    private TextView email;
    private EditText password;


    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebase = LibraryClass.getFirebase();
        verifyUserLoogged();
        initViews();
        ButterKnife.bind(this);


        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SingupActivity.class);
                startActivityForResult(intent, REQUEST_SINGUP);
            }
        });

    }

    public void singUp(){
        Intent intent = new Intent(this, SingupActivity.class);
        startActivity(intent);
    }

    protected void initViews() {
        name = (TextView) findViewById(R.id.input_name);
        email = (TextView) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);


        //progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
    }

    protected void initUser() {
        user = new User();
       // user.setNome(name.getText().toString());
//        user.setEmail(email.getText().toString());
  //      user.setPassword(password.getText().toString());
    }

    public void callSignUp(View view) {
        Intent intent = new Intent(this, SingupActivity.class);
        startActivity(intent);
    }

    public void sendLonginData(View view) {
        initUser();
    }

    private void verifyUserLoogged() {
        if (firebase.getAuth() != null) {
            callCardViewActivity();
        }
        else {
            initUser();
        }

            if (!user.getTokenSP(this).isEmpty()){
                firebase.authWithPassword(
                        "password",
                        user.getTokenSP(this),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {

                                user.saveTokenSP(LoginActivity.this, authData.getToken());
                                callCardViewActivity();
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {

                            }
                        }
                );

            }
        }


    private void callCardViewActivity() {
        Intent intent = new Intent(this, CardViewActivity.class);
        startActivity(intent);
        finish();
    }

    private void verifyLogin() {
        firebase.authWithPassword(
                user.getEmail(),
                user.getPassword(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        user.saveTokenSP(LoginActivity.this, authData.getToken());
                        callCardViewActivity();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        finish();
                    }
                }
        );

    }
}
