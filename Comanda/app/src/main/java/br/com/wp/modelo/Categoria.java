package br.com.wp.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 26/11/2016.
 */

public class Categoria implements Serializable {

    private Long id;
    private String categoria;
    private String secao;
    private static Categoria instance;
    private static ArrayList<Categoria> listaCategorias;

    public Categoria(){}


    public static ArrayList<Categoria> getListaCategorias() {
        if(listaCategorias == null ){
            listaCategorias = new ArrayList<>();
        }
        return listaCategorias;
    }

    public static void setListaCategorias(ArrayList<Categoria> listaCategorias) {
        Categoria.listaCategorias = listaCategorias;
    }

    public static Categoria getInstance() {
        if(instance == null){
            instance = new Categoria();
        }
        return instance;
    }

    public static void setInstance(Categoria instance) {
        Categoria.instance = instance;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }


}
