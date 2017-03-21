package br.com.wp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import br.com.wp.comanda.R;
import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Pedido;
import br.com.wp.modelo.Quantidade;

/**
 * Created by User on 25/11/2016.
 */

public class NovoPedidoAdapter extends BaseAdapter {


    Context context;
    private ArrayList<Pedido> listaPedidos;
    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    public NovoPedidoAdapter(Context context, ArrayList<Pedido> listaPedidos){
        this.listaPedidos = listaPedidos;
        this.context=context;
    }

    public NovoPedidoAdapter(){

    }

    @Override
    public int getCount() {
        return listaPedidos.size();
    }

    @Override
    public Object getItem(int position) {

        return listaPedidos.get(position);
    }


    @Override
    public long getItemId(int i) {

        return 0;
    }

    public void atualizarMesa(Mesa mesa){

        for(Pedido ped : Pedido.getListaPedidos()) {
            ped.setMesa(mesa);
        }
        notifyDataSetChanged();
    }

    public void atualizarCartao(Cartao cartao){

        for(Pedido ped : Pedido.getListaPedidos()) {
            ped.setCartao(cartao);
        }
        notifyDataSetChanged();
    }

    public void atualizarQtde(Pedido pedido, Quantidade qtde){

        pedido.setQuantidade(qtde);
        notifyDataSetChanged();
    }

    public void excluirPedido(Pedido pedido){

        listaPedidos.remove(pedido);

        notifyDataSetChanged();
    }

    public void excluirPedidoPorItemCardapio(Cardapio itemCardapio){


        Iterator iter = Pedido.getListaPedidos().iterator();
        while (iter.hasNext()) {

            Pedido ped = (Pedido) iter.next();
            if (ped.getItemCardapio().equals(itemCardapio)) {

                iter.remove();
            }
        }
        notifyDataSetChanged();
    }

    public void cancelarPedido(){

        Pedido.getListaPedidos().clear();
        Cardapio.getListaItensSelecionados().clear();

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        Pedido pedido = listaPedidos.get(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.novo_pedido, null);

            holder = new ViewHolder();

            holder.item = (TextView)
                    convertView.findViewById(R.id.tvNomeItem);

            holder.valor = (TextView)
                    convertView.findViewById(R.id.tvValor);

            holder.quantidade = (TextView)
                    convertView.findViewById(R.id.tvQuantidade);


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.item.setText(pedido.getItemCardapio().getNome());
        holder.quantidade.setText(pedido.getQuantidade().getQuantString());
        String aux = nf.format(pedido.getItemCardapio().getValor());
        holder.valor.setText(aux);

        return convertView;
    }

    static class ViewHolder {
        TextView item;
        TextView valor;
        TextView quantidade;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

}
