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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Categoria;
import br.com.wp.modelo.Usuario;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonCardapio {

    public ArrayList<Cardapio> converteJsonParaListaCardapio(String strCardapio) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cardapio>>() {
        }.getType();

        ArrayList<Cardapio> listaCardapio = gson.fromJson(strCardapio, type);

        return listaCardapio;
    }
    }
