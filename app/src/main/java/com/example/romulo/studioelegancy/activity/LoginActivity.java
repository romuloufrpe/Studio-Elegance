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



    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        firebase = LibraryClass.getFirebase();
        verifyUserLogged();

        _emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SingupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(){
        Log.d(TAG,"Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Confirmando...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();



        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                },3000);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_SINGUP){
            if(resultCode == RESULT_OK){
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void onLoginSuccess(){
        _loginButton.setEnabled(true);

        finish();
    }

    public void onLoginFailed(){
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("Entre com um email valido");
            valid = false;
        }
        if(password.isEmpty() || password.length()< 4 || password.length() > 10){
            _passwordText.setError("Senha deve conter de 4 a 10 caracteres");
            valid = false;
        }else{
            _passwordText.setError(null);
        } if(email.equals("romulo@gmail.com")  && password.equals("12345") ){
            Intent intent = new Intent(getApplicationContext(), CardViewActivity.class);
            startActivity(intent);
            valid = true;
        }

        return valid;

    }
    public void sendLoginData(){

    }

    private void verifyUserLogged(){
        if (firebase.getAuth() !=null){
          callMainActivity();
        }
    }
    private void callMainActivity(){
        Intent intent = new Intent(this,CardView.class);
        startActivity(intent);
        finish();
    }

    private void verifyLogin(){
        firebase.authWithPassword(
                user.getEmail(),
                user.getPassword(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        user.saveTokenSP(LoginActivity.this, authData.getToken());
                        callMainActivity();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                            finish();
                    }
                }
        );
    }
}
