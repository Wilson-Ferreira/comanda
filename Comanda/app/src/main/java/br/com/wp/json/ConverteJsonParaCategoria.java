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
import java.util.List;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Categoria;

/**
 * Created by User on 29/11/2016.
 */

public class ConverteJsonParaCategoria {

    public ArrayList<Categoria> converteJsonParaListaCategoria(String strCategoria) throws IOException {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Categoria>>() {
        }.getType();

        ArrayList<Categoria> listaCategoria = gson.fromJson(strCategoria, type);

        return listaCategoria;

    }
}
