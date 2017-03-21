package br.com.wp.comanda;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.com.wp.adapter.CardapioAdapter;
import br.com.wp.adapter.NovoPedidoAdapter;
import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Categoria;


public class CardapioActivity extends MainActivity {

    private CardapioAdapter adapter;
    private ListView lista;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.itens_do_cardapio);

        setSupportActionBar(toolbar);

        Button proxima = (Button) findViewById(R.id.btnProximo);

        lista = (ListView) findViewById(R.id.listaCardapio);
        lista.setOnItemClickListener(onItemClickListener);

        atualizarAdapter();

        if (adapter != null) {

            adapter.pesquisarNaLista(Categoria.getInstance().getCategoria());
        }

        proxima.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (Cardapio.getListaItensSelecionados().isEmpty()) {
                    mensagem(getString(R.string.selecione_um_item));
                    return;
                }

                intent = new Intent(getApplicationContext(), NovoPedidoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            NovoPedidoAdapter novoPedidoAdapter = new NovoPedidoAdapter();

            Cardapio cardapio = Cardapio.getListaCardapio().get(i);

            if (!Cardapio.getListaItensSelecionados().contains(cardapio)) {

                Cardapio.getListaItensSelecionados().add(cardapio);

            } else {

                Cardapio.getListaItensSelecionados().remove(cardapio);

                novoPedidoAdapter.excluirPedidoPorItemCardapio(cardapio);
            }

            adapter.notifyDataSetChanged();
        }
    };

    private void atualizarAdapter() {
        adapter = new CardapioAdapter(
                CardapioActivity.this, Cardapio.getListaCardapio());
        lista.setAdapter(adapter);
    }

    public void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {

                intent = new Intent(getApplicationContext(),CategoriaActivity.class);
                startActivity(intent);
                finish();
            }
}
