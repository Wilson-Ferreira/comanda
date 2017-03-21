package br.com.wp.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 03/12/2016.
 */

public class Cartao implements Serializable {

    private Long id;

    private String numeroCartao;

    private static Cartao instance;
    private static ArrayList<Cartao> listaCartao;

    public static ArrayList<Cartao> getListaCartao() {
        if(listaCartao == null){
            listaCartao = new ArrayList<>();
        }
        return listaCartao;
    }

    public static void setListaCartao(ArrayList<Cartao> listaCartao) {
        Cartao.listaCartao = listaCartao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
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

    public static Cartao getInstance() {
        if(instance == null){
            instance = new Cartao();
        }
        return instance;
    }

    public static void setInstance(Cartao instance) {
        Cartao.instance = instance;
    }

}
