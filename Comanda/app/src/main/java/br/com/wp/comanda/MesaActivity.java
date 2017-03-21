package br.com.wp.comanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import br.com.wp.adapter.MesaAdapter;
import br.com.wp.adapter.NovoPedidoAdapter;
import br.com.wp.modelo.Configuracao;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Pedido;

public class MesaActivity extends MainActivity {

    private Mesa mesa;
    private Intent intent;
    private GridView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.mesas);
        setSupportActionBar(toolbar);

        lista = (GridView) findViewById(R.id.gridview);


            atualizarAdapter();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mesa = Mesa.getInstance().getListaMesas().get(position);

                Mesa.setInstance(mesa);

                if(!Pedido.getInstance().getListaPedidos().isEmpty()) {

                    NovoPedidoAdapter adapter = new NovoPedidoAdapter();
                    adapter.atualizarMesa(mesa);

                }
                    intent = new Intent(getApplicationContext(), CategoriaActivity.class);
                    startActivity(intent);
                    finish();

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO")) {

            intent = new Intent(getApplicationContext(), CartaoActivity.class);
            startActivity(intent);
            finish();

        } else {

            intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();
        }
            }

    public void atualizarAdapter() {
        MesaAdapter adapter = new MesaAdapter(
                MesaActivity.this, Mesa.getListaMesas());
        lista.setAdapter(adapter);
    }
}


