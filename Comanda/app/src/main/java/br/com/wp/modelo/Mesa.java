package br.com.wp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25/11/2016.
 */

public class Mesa implements Serializable {

    private  Long id;
    private  String numeroMesa;
    private static ArrayList<Mesa> listaMesas;
    private static Mesa instance;

    public static ArrayList<Mesa> getListaMesas() {
        if(listaMesas == null){
            listaMesas = new ArrayList<>();
        }
        return listaMesas;
    }

    public static void setListaMesas(ArrayList<Mesa> listaMesas) {
        Mesa.listaMesas = listaMesas;
    }

    public static Mesa getInstance() {

        if(instance == null){
            instance = new Mesa();
        }
        return instance;
    }

    public static void setInstance(Mesa instance) {
        Mesa.instance = instance;
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

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
}
