package br.com.wp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.wp.comanda.R;
import br.com.wp.modelo.Mesa;

/**
 * Created by User on 25/11/2016.
 */

public class MesaAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Mesa> listaMesas;

    public MesaAdapter(Context context, ArrayList<Mesa> listaMesas){
        this.listaMesas = listaMesas;
        this.context=context;

    }

    @Override
    public int getCount() {
        return listaMesas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMesas.get(position);
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
                    R.layout.mesa_grid, parent,false);

            listViewHolder = new ViewHolder();

            listViewHolder.numeroMesa = (TextView)convertView.findViewById(R.id.numero);

            convertView.setTag(listViewHolder);

        }else{

            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.numeroMesa.setText(listaMesas.get(position).getNumeroMesa());

        return convertView;

    }

    public static class ViewHolder
    {

        public TextView numeroMesa;

    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Mesa> getListaMesas() {
        return listaMesas;
    }
}
