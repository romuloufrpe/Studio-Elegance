package com.example.romulo.studioelegancy.domain;

import java.io.Serializable;

/**
 * Created by romulo on 28/07/16.
 */
public class Pedido implements Serializable {

    private String model;
    private int photo;


    public Pedido(){}
    public Pedido(String m, int p){
        model = m;
        photo = p;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}
