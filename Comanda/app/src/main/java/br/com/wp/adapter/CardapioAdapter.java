package br.com.wp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import br.com.wp.modelo.Categoria;

/**
 * Created by Wilson F Florindo on 25/11/2016.
 */

public class CardapioAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Cardapio> listaCardapio;

    private NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public CardapioAdapter(Context context, ArrayList<Cardapio> listaCardapio) {
        this.listaCardapio = listaCardapio;
        this.context = context;
    }

    public CardapioAdapter() {
    }

    @Override
    public int getCount() {
        return listaCardapio.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCardapio.get(position);
    }

    public void pesquisarNaLista(String categoria) {

        for (Cardapio cardapio : Cardapio.getListaCardapioAux()) {

            if (!Cardapio.getListaCardapio().contains(cardapio)) {

                Cardapio.getListaCardapio().add(cardapio);
            }
        }

        if (categoria == null) {
            return;
        }

        if (!categoria.equalsIgnoreCase("TODAS")) {

            Iterator iter = Cardapio.getListaCardapio().iterator();
            while (iter.hasNext()) {

                Cardapio card = (Cardapio) iter.next();
                if (!card.getCategoria().getCategoria().equalsIgnoreCase(categoria)) {

                    iter.remove();
                }
            }
        }

        notifyDataSetChanged();
    }

    public boolean pesquisarCategoria(Categoria categoria) {

        boolean retorno = false;

        if (!categoria.getCategoria().equalsIgnoreCase("TODAS")) {

            for (Cardapio card : Cardapio.getListaCardapioAux()) {

                if (card.getCategoria().getCategoria().equalsIgnoreCase(categoria.getCategoria())) {
                    retorno = true;
                    break;
                }
            }

        } else {

            retorno = true;
        }
        return retorno;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    private boolean marcarViewSelecionada(Cardapio itemCardapio) {

        if (Cardapio.getListaItensSelecionados().contains(itemCardapio)) return true;

        return false;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        String disp;

        Cardapio cardapio = listaCardapio.get(position);


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.cardapio, null);

            holder = new ViewHolder();

            holder.item = (TextView)
                    convertView.findViewById(R.id.tvNomeItem);

            holder.valor = (TextView)
                    convertView.findViewById(R.id.tvValor);

            holder.disponivel = (TextView)
                    convertView.findViewById(R.id.tvDisponivel);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.item.setText(cardapio.getNome());
        String aux = nf.format(cardapio.getValor());
        holder.valor.setText(aux);


        if (cardapio.isDisponivel()) {

            disp = "Sim";
        } else {
            disp = "Não";
        }

        holder.disponivel.setText(disp);

        if (marcarViewSelecionada(cardapio)) {

            convertView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorItemSelecionadoList));


        } else {

            convertView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorList));

        }


        return convertView;
    }

    private Context getContext() {
        return context;
    }


    private ArrayList<Cardapio> getListaCardapio() {
        return listaCardapio;
    }

    private static class ViewHolder {
        TextView item;
        TextView valor;
        TextView disponivel;
    }

}
