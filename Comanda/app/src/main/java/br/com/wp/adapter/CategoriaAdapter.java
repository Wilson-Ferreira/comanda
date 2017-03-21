package br.com.wp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.wp.comanda.R;
import br.com.wp.modelo.Categoria;

/**
 * Created by User on 25/11/2016.
 */

public class CategoriaAdapter extends BaseAdapter {

    Context context;
    private static ArrayList<Categoria> listaCategorias;

    public CategoriaAdapter(Context context, ArrayList<Categoria> listaCategorias){
        this.listaCategorias = listaCategorias;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listaCategorias.size();
    }

    @Override
    public Object getItem(int position) {

        return listaCategorias.get(position);
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
                    R.layout.categoria_grid, parent,false);

            listViewHolder = new ViewHolder();

            listViewHolder.nomeCategoria = (TextView)convertView.findViewById(R.id.categoria);

            convertView.setTag(listViewHolder);

        }else{

            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.nomeCategoria.setText(listaCategorias.get(position).getCategoria().toString());

        return convertView;

    }

    public static class ViewHolder
    {

        public TextView nomeCategoria;

    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Categoria> getListaCartoes() {
        return listaCategorias;
    }
}
