package br.com.wp.json;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Usuario;


/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class ConverteJsonUsuario {

    public String converteUsuarioParaJson(Usuario usuario) {

        Gson gson = new Gson();
        String usuarioJSONString = gson.toJson(usuario);

        return usuarioJSONString;
    }
    }
