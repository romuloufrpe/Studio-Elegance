package com.example.romulo.studioelegancy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.romulo.studioelegancy.R;
import com.example.romulo.studioelegancy.domain.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romulo on 28/07/16.
 */
public class CardViewActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedExtendeState){
        super.onCreate(savedExtendeState);
        setContentView(R.layout.card_activity);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public List<Pedido> getsetPedidoList(int qnt){
        String[] tipo = new String[]{"Cabelo","Manicure","Pedicure","Depilação"};
        int[]photos = new int[]{R.drawable.medi1};
        List<Pedido> listAux = new ArrayList<>();
        for(int i = 0 ; i < qnt; i++){
            Pedido p = new Pedido(tipo[i% tipo.length],photos[i% photos.length]);
            listAux.add(p);
        }
        return listAux;

    }
}
