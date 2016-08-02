package com.example.romulo.studioelegancy.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by romulo on 28/07/16.
 */
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.romulo.studioelegancy.R;
import com.example.romulo.studioelegancy.User;
import com.example.romulo.studioelegancy.domain.util.LibraryClass;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseUser;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.Map;

public class SingupActivity extends AppCompatActivity  {


    private User user;
    private TextView name;
    private TextView email;
    private EditText password;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebase = LibraryClass.getFirebase();
        initViews();
    }



    protected void initViews(){
        name = (TextView) findViewById(R.id.input_name);
        email = (TextView) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        //progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
    }

    protected void initUser(){
        user = new User();
        user.setNome( name.getText().toString() );
        user.setEmail( email.getText().toString() );
        user.setPassword( password.getText().toString() );
    }

    public void sendSignUpData( View view ){
        //openProgressBar();
        initUser();
        saveUser();

    }

    private void saveUser(){
        firebase.createUser(
                user.getEmail(),
                user.getPassword(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        user.setId( stringObjectMap.get("uid").toString());
                        user.saveDB();
                        firebase.unauth();
                        finish();

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                }
        );
    }


}
