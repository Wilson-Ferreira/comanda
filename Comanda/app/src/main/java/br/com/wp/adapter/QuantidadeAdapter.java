package br.com.wp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.wp.comanda.R;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Quantidade;

/**
 * Created by User on 25/11/2016.
 */

public class QuantidadeAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Quantidade> listaQuantidades;

    public QuantidadeAdapter(Context context, ArrayList<Quantidade> listaQuantidades){
        this.listaQuantidades = listaQuantidades;
        this.context=context;

    }

    @Override
    public int getCount() {
        return listaQuantidades.size();
    }

    @Override
    public Object getItem(int position) {
        return listaQuantidades.get(position);
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
                    R.layout.quantidade_grid, parent,false);

            listViewHolder = new ViewHolder();

            listViewHolder.quantidade = (TextView)convertView.findViewById(R.id.quantidade);

            convertView.setTag(listViewHolder);

        }else{

            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.quantidade.setText(listaQuantidades.get(position).getQuantString());

        return convertView;

    }

    public static class ViewHolder
    {

        public TextView quantidade;

    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Quantidade> getListaQuantidades() {
        return listaQuantidades;
    }
}
