package br.com.wp.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Mesa;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonMesa {

    public ArrayList<Mesa> converteJsonParaListaMesa(String strMesa) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Mesa>>() {
        }.getType();

        ArrayList<Mesa> listaMesa = gson.fromJson(strMesa, type);

        return listaMesa;
    }
    }
