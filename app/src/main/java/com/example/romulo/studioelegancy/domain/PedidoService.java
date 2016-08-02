package com.example.romulo.studioelegancy.domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romulo on 28/07/16.
 */
public class PedidoService {

    public static List<Pedido> getPedido(Context context){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        for (int i = 0; i < 20 ; i++){
            Pedido p = new Pedido();


            pedidos.add(p);
        }
        return pedidos;
    }
}
