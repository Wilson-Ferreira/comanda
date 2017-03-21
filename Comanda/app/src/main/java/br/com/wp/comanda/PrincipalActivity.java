package br.com.wp.comanda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import br.com.wp.Util.AndroidUtil;
import br.com.wp.json.ConverteJsonCardapio;
import br.com.wp.json.ConverteJsonMesa;
import br.com.wp.json.ConverteJsonParaCategoria;
import br.com.wp.json.ConverteJsonQuantidade;
import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Categoria;
import br.com.wp.modelo.Configuracao;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Pedido;
import br.com.wp.modelo.Quantidade;

public class PrincipalActivity extends MainActivity {

    private ProgressDialog dialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.principal);
        setSupportActionBar(toolbar);


        dialog = new ProgressDialog(getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

       Button novoPedido = (Button) findViewById(R.id.novoPedido);
       Button pedidosPorCliente = (Button) findViewById(R.id.pesquisar);
       Button sincronizarDados = (Button) findViewById(R.id.sincronizar_dados);


        sincronizarDados.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                sincronizarDados();
            }
        });

        pedidosPorCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });



        novoPedido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO")) {

                    intent = new Intent(getApplicationContext(), CartaoActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    intent = new Intent(getApplicationContext(), MesaActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {

                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
    }


    private void sincronizarDados() {

        if (Configuracao.getInstance().getTipoCobranca() != null) {

            Cardapio.getListaCardapio().clear();
            Cardapio.getListaCardapioAux().clear();
            Cardapio.getListaItensSelecionados().clear();
            Quantidade.getListaQuantidades().clear();
            Mesa.getListaMesas().clear();

            Categoria.getListaCategorias().clear();
            Pedido.getListaPedidos().clear();
            Categoria.setInstance(null);

            AsyncTaskBuscarQuantidades asyncTaskBuscarQuantidades = new AsyncTaskBuscarQuantidades();
            asyncTaskBuscarQuantidades.execute();

        }
    }

        private class AsyncTaskBuscarQuantidades extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {

                dialog.setTitle(getString(R.string.carregando_qtdes));
                dialog.show();
            }

            @Override
            protected String doInBackground(Void... strings) {

                StringBuilder resultado;

                String metodoWs = "quantidade/buscarQuantidades";

                AndroidUtil androidUtil = new AndroidUtil();
                resultado = androidUtil.metodoGet(metodoWs);

                return resultado.toString();

            }

            @Override
            protected void onPostExecute(String resultado) {

                try {

                    ConverteJsonQuantidade converteJsonQuantidade = new ConverteJsonQuantidade();
                    ArrayList<Quantidade> listaQuantidades;
                    listaQuantidades = converteJsonQuantidade.converteJsonParaListQuantidade(resultado);

                    Quantidade.setListaQuantidades(listaQuantidades);

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    AsyncTaskCardapio asyncTaskCardapio = new AsyncTaskCardapio();
                    asyncTaskCardapio.execute();

                } catch (IOException e) {

                    mensagem(getString(R.string.servidor_nao_retornou));
                }
            }
    }

    private class AsyncTaskCardapio extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            dialog.setTitle(getString(R.string.carregando_cardapio));
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder resultado;

            String metodoWs = "cardapio/buscarTodosGSON";

            AndroidUtil androidUtil = new AndroidUtil();
            resultado = androidUtil.metodoGet(metodoWs);

            return resultado.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {

            ConverteJsonCardapio converteJsonCardapio = new ConverteJsonCardapio();

            Cardapio.getListaCardapio().clear();
            Cardapio.getListaCardapioAux().clear();

            if (resultado != null) {

                try {

                    List<Cardapio> listaCardapio;
                    listaCardapio = converteJsonCardapio.converteJsonParaListaCardapio(resultado);

                    Cardapio.getListaCardapio().addAll(listaCardapio);
                    Cardapio.getListaCardapioAux().addAll(listaCardapio);

                } catch (IOException e) {

                    e.printStackTrace();
                }

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                AsyncTaskCategorias asyncTaskCategorias = new AsyncTaskCategorias();
                asyncTaskCategorias.execute();

            } else {

                mensagem(getString(R.string.servidor_nao_retornou));
            }
        }
    }

    private class AsyncTaskCategorias extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            dialog.setTitle(getString(R.string.carregando_categorias));
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... strings) {

            StringBuilder resultado;

            String metodoWs = "categoria/buscarCategorias";

            AndroidUtil androidUtil = new AndroidUtil();
            resultado = androidUtil.metodoGet(metodoWs);

            return resultado.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado != null) {

                ConverteJsonParaCategoria converteJsonParaCategoria = new ConverteJsonParaCategoria();

                try {

                    Categoria.getListaCategorias().addAll(converteJsonParaCategoria.converteJsonParaListaCategoria(resultado));

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    AsyncTaskMesa asyncTaskMesa = new AsyncTaskMesa();
                    asyncTaskMesa.execute();

                } catch (IOException e) {

                    e.printStackTrace();
                }

            } else {

                mensagem(getString(R.string.servidor_nao_retornou));
            }
        }
    }
    private class AsyncTaskMesa extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            dialog.setTitle(getString(R.string.carregando_mesas));
            dialog.show();

        }

        @Override
        protected String doInBackground(Void... strings) {

            StringBuilder resultado;
            String metodoWs = "mesa/buscarMesas";

            AndroidUtil androidUtil = new AndroidUtil();
            resultado = androidUtil.metodoGet(metodoWs);

            return resultado.toString();

        }

        @Override
        protected void onPostExecute(String resultado) {

            if (resultado != null) {

                try {

                    ConverteJsonMesa converteJsonMesa = new ConverteJsonMesa();

                    Mesa.getListaMesas().addAll(converteJsonMesa.converteJsonParaListaMesa(resultado));


                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                mensagem(getString(R.string.servidor_nao_retornou));
            }
        }
    }

    private void mensagem(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}