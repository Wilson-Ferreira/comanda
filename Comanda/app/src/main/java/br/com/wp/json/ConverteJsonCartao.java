package br.com.wp.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Mesa;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonCartao {

    public ArrayList<Cartao> converteJsonParaListaCartao(String strCartao) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cartao>>() {
        }.getType();

        ArrayList<Cartao> listaCartao = gson.fromJson(strCartao, type);

        return listaCartao;
    }
    }
