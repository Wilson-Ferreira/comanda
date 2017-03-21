package br.com.wp.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25/11/2016.
 */

public class Quantidade implements Serializable {


    private Long id;
    private BigDecimal quantidade;
    private String quantString;
    private static ArrayList<Quantidade> listaQuantidades;
    private static Quantidade instance;

    public static ArrayList<Quantidade> getListaQuantidades() {

        if(listaQuantidades == null){
            listaQuantidades = new ArrayList<>();
        }
        return listaQuantidades;
    }

    public static void setListaQuantidades(ArrayList<Quantidade> listaQuantidades) {
        Quantidade.listaQuantidades = listaQuantidades;
    }

    public static Quantidade getInstance() {

        if(instance == null){
            instance = new Quantidade();
        }
        return instance;
    }

    public static void setInstance(Quantidade instance) {
        Quantidade.instance = instance;
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


    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getQuantString() {
        return quantString;
    }

    public void setQuantString(String quantString) {
        this.quantString = quantString;
    }
}
