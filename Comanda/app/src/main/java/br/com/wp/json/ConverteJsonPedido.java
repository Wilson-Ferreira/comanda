package br.com.wp.json;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Pedido;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonPedido {


    public String converteListaPedidoParaJson(ArrayList<Pedido> listaPedidos){

        Gson gson = new Gson();

        String json = new Gson().toJson(listaPedidos);

        Log.i("json ",json.toString());
        return json;
    }
}
