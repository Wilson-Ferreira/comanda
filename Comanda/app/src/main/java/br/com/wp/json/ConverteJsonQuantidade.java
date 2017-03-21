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
import java.util.List;

import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Quantidade;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonQuantidade {

    public ArrayList<Quantidade> converteJsonParaListQuantidade(String strQuantidade) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Quantidade>>() {
        }.getType();

        ArrayList<Quantidade> listaQuantidades = gson.fromJson(strQuantidade, type);

        return listaQuantidades;
    }
    }
