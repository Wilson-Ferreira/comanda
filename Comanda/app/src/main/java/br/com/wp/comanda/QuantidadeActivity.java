package br.com.wp.comanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import br.com.wp.adapter.NovoPedidoAdapter;
import br.com.wp.adapter.QuantidadeAdapter;
import br.com.wp.modelo.Pedido;
import br.com.wp.modelo.Quantidade;

public class QuantidadeActivity extends MainActivity {

    private Intent intent;
    private GridView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantidade);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.quantidades);
        setSupportActionBar(toolbar);

        lista = (GridView) findViewById(R.id.gridview_quantidade);
        lista.setOnItemClickListener(onItemClickListener);

        atualizarAdapter();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Quantidade qtde = Quantidade.getListaQuantidades().get(i);
            Quantidade.setInstance(qtde);
            NovoPedidoAdapter adapter = new NovoPedidoAdapter();
            adapter.atualizarQtde(Pedido.getInstance(),qtde);

            intent = new Intent(getApplicationContext(), NovoPedidoActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public void atualizarAdapter() {

        QuantidadeAdapter adapter = new QuantidadeAdapter(
                QuantidadeActivity.this, Quantidade.getListaQuantidades());
        lista.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
                intent = new Intent(getApplicationContext(),NovoPedidoActivity.class);
                startActivity(intent);
                finish();
            }
}


