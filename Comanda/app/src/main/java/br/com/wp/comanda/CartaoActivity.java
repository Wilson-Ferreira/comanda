package br.com.wp.comanda;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;

import br.com.wp.Util.AndroidUtil;
import br.com.wp.adapter.CartaoAdapter;
import br.com.wp.adapter.NovoPedidoAdapter;
import br.com.wp.json.ConverteJsonCartao;
import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Pedido;

import static br.com.wp.comanda.R.id.gridview;

public class CartaoActivity extends MainActivity {

    private Cartao cartao;
    private Intent intent;
    private GridView lista;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.cartoes);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        lista = (GridView) findViewById(gridview);

        AsyncTaskCartao asyncTaskCartao = new AsyncTaskCartao();
        asyncTaskCartao.execute();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cartao = Cartao.getInstance().getListaCartao().get(position);
                Cartao.setInstance(cartao);

                if (!Pedido.getInstance().getListaPedidos().isEmpty()) {

                    NovoPedidoAdapter adapter = new NovoPedidoAdapter();
                    adapter.atualizarCartao(cartao);
                }

                intent = new Intent(getApplicationContext(), MesaActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {

        intent = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(intent);
        finish();
    }


    public void atualizarAdapter() {
       CartaoAdapter adapter = new CartaoAdapter(
                CartaoActivity.this, Cartao.getListaCartao());
        lista.setAdapter(adapter);
    }

    private class AsyncTaskCartao extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            dialog.setTitle(getString(R.string.carregando_cartoes));
            dialog.show();

        }

        @Override
        protected String doInBackground(Void... strings) {

            StringBuilder resultado;

            String metodoWs = "cartao/buscarCartoes";

            AndroidUtil androidUtil = new AndroidUtil();
            resultado = androidUtil.metodoGet(metodoWs);

            return resultado.toString();
        }

        @Override
        protected void onPostExecute(String strRetorno) {


            if(strRetorno.contains("{")){

                ConverteJsonCartao converteJsonCartao = new ConverteJsonCartao();

                try {

                    Cartao.getListaCartao().clear();
                    Cartao.getListaCartao().addAll(converteJsonCartao.converteJsonParaListaCartao(strRetorno));

                    atualizarAdapter();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                mensagem(strRetorno);
            }

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    private void mensagem(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}

