package br.com.wp.modelo;

import java.io.Serializable;

/**
 * Created by User on 03/12/2016.
 */

public class Configuracao implements Serializable {

    private Long id;
    private String tipoCobranca;
    private static Configuracao instance;
    private static String url;

    public Configuracao(){}

    public static Configuracao getInstance() {
        if(instance == null ){
            instance = new Configuracao();
        }
        return instance;
    }

    public static void setInstance(Configuracao instance) {
        Configuracao.instance = instance;
    }

    public static String getUrl() {
        return "http://192.168.0.107:8080/wp/rest/";
    }

    public static void setUrl(String url) {
        Configuracao.url = "http://192.168.0.107:8080/wp/rest/";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }
}
