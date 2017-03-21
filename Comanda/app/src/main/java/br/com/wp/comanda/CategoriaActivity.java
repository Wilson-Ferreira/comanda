package br.com.wp.comanda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import br.com.wp.adapter.CardapioAdapter;
import br.com.wp.adapter.CategoriaAdapter;
import br.com.wp.modelo.Categoria;

public class CategoriaActivity extends MainActivity {

    private GridView lista;
    private Categoria categoria;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.categorias);
        setSupportActionBar(toolbar);

        lista = (GridView) findViewById(R.id.gridview);

        atualizarAdapter();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                categoria = Categoria.getListaCategorias().get(position);
                Categoria.setInstance(categoria);

                CardapioAdapter cardapioAdapter = new CardapioAdapter();

                if (cardapioAdapter.pesquisarCategoria(categoria)) {

                    intent = new Intent(getApplicationContext(), CardapioActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    mensagem(getString(R.string.não_ha) + Categoria.getInstance().getCategoria().toLowerCase() + getString(R.string.cadastrados));

                }
            }
        });
    }

    @Override
    public void onBackPressed() {

                intent = new Intent(getApplicationContext(),MesaActivity.class);
                startActivity(intent);
                finish();
            }

    private void atualizarAdapter() {
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(
                CategoriaActivity.this, Categoria.getListaCategorias());
        lista.setAdapter(categoriaAdapter);
    }

    public void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
