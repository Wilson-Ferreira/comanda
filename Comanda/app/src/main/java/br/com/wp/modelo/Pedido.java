package br.com.wp.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 25/11/2016.
 */

public class Pedido implements Serializable {

    private Long id;
    private Funcionario funcionario;
    private Cardapio itemCardapio;
    private Quantidade quantidade;
    private String statusPedido;;
    private Mesa mesa;

    private Date dataPedido;
    private Cartao cartao;
    private static Pedido instance;


    private static ArrayList<Pedido> listaPedidos;

    public static ArrayList<Pedido> getListaPedidos() {
        if(listaPedidos == null){
            listaPedidos = new ArrayList<>();
        }
        return listaPedidos;
    }

    public static void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        Pedido.listaPedidos = listaPedidos;
    }

    public static Pedido getInstance() {
        if(instance == null){
            instance = new Pedido();
        }
        return instance;
    }

    public static void setInstance(Pedido instance) {
        Pedido.instance = instance;
    }


    public Cardapio getItemCardapio() {
        return itemCardapio;
    }

    public void setItemCardapio(Cardapio itemCardapio) {
        this.itemCardapio = itemCardapio;
    }

    public Quantidade getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Quantidade quantidade) {
        this.quantidade = quantidade;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (itemCardapio == null) {
            if (other.itemCardapio != null)
                return false;
        } else if (!itemCardapio.equals(other.itemCardapio))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemCardapio == null) ? 0 : itemCardapio.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "br.com.wp.modelo.Pedido[ itemCardapio=" + itemCardapio + " ]";
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
