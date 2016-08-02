package com.example.romulo.studioelegancy;

import android.content.Context;

import com.example.romulo.studioelegancy.domain.util.LibraryClass;
import com.firebase.client.Firebase;

/**
 * Created by romulo on 29/07/16.
 */
public class User {

    private static String TOKEN = "com.exmaple.romulo.studioelegance.TOKEN";

    public String id;
    public String nome;
    public String email;
    public String password;

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveTokenSP(Context context, String token){
        LibraryClass.saveSP(context, TOKEN,token);
    }

    public String getTokenSP(Context context){
        String token = LibraryClass.getSP(context, TOKEN);
        return token;
    }

    public void saveDB(){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("users").child(getId());

        setPassword(null);
        setId(null);
        firebase.setValue(this);
    }
}
