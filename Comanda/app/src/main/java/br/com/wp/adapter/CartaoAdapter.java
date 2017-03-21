package br.com.wp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.wp.comanda.R;
import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Mesa;

/**
 * Created by User on 25/11/2016.
 */

public class CartaoAdapter extends BaseAdapter {

    Context context;
    private static ArrayList<Cartao> listaCartoes;

    public CartaoAdapter(Context context, ArrayList<Cartao> listaCatoes){
        this.listaCartoes = listaCatoes;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listaCartoes.size();
    }

    @Override
    public Object getItem(int position) {

        return listaCartoes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.cartao_grid, parent,false);

            listViewHolder = new ViewHolder();

            listViewHolder.numeroCartao = (TextView)convertView.findViewById(R.id.numero);

            convertView.setTag(listViewHolder);

        }else{

            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.numeroCartao.setText(listaCartoes.get(position).getNumeroCartao().toString());

        return convertView;

    }

    public static class ViewHolder
    {

        public TextView numeroCartao;

    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Cartao> getListaCartoes() {
        return listaCartoes;
    }
}
