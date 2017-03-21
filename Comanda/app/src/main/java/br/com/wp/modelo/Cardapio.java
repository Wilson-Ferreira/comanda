package br.com.wp.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Wilson F Florindo on 19/11/2016.
 */

public class Cardapio implements Serializable {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private boolean disponivel;
    private String descricao;
    private Categoria categoria;
   // private String nomeCategoria;
    private static Cardapio instance;
    private static ArrayList<Cardapio> listaItensSelecionados;
    private static ArrayList<Cardapio> listaCardapio;
    private static ArrayList<Cardapio> listaCardapioAux;


    public Cardapio() {

    }

    public static ArrayList<Cardapio> getListaCardapio() {
        if(listaCardapio == null){
            listaCardapio = new ArrayList<>();
        }
        return listaCardapio;
    }

    public static void setListaCardapio(ArrayList<Cardapio> listaCardapio) {
        Cardapio.listaCardapio = listaCardapio;
    }

    public static ArrayList<Cardapio> getListaItensSelecionados() {

        if(listaItensSelecionados == null){
            listaItensSelecionados = new ArrayList<>();
        }
        return listaItensSelecionados;
    }

    public static void setListaItensSelecionados(ArrayList<Cardapio> listaItensSelecionados) {
        Cardapio.listaItensSelecionados = listaItensSelecionados;
    }

    public static Cardapio getInstance() {

        if (instance == null){

            instance = new Cardapio();
        }
        return instance;
    }


    public static void setInstance(Cardapio instance) {
        Cardapio.instance = instance;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cardapio other = (Cardapio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Cardapio{" + "id=" + id + '}';
    }



    public Cardapio(Long id, String nome, BigDecimal valor, boolean disponivel){

        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.disponivel = disponivel;

    }

    public static ArrayList<Cardapio> getListaCardapioAux() {
        if(listaCardapioAux == null){
            listaCardapioAux = new ArrayList<>();
        }

        return listaCardapioAux;
    }

    public static void setListaCardapioAux(ArrayList<Cardapio> listaCardapioAux) {
        Cardapio.listaCardapioAux = listaCardapioAux;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

/*
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
*/

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
