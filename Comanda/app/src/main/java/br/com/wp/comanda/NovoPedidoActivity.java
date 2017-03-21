package br.com.wp.comanda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import br.com.wp.Util.AndroidUtil;
import br.com.wp.adapter.NovoPedidoAdapter;
import br.com.wp.json.ConverteJsonPedido;
import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Configuracao;
import br.com.wp.modelo.Funcionario;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Pedido;
import br.com.wp.modelo.Quantidade;

public class NovoPedidoActivity extends MainActivity implements PopupMenu.OnMenuItemClickListener {

    private Intent intent;
    private ListView lista;
    private TextView numMesa;
    private TextView numCartao;
    private NovoPedidoAdapter adapter;
    private ProgressDialog dialog;
    private Pedido pedido;
    private Quantidade quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setTitle(R.string.novo_pedido);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        Button salvarPedido = (Button) findViewById(R.id.btnSalvar);

        numMesa = (TextView) findViewById(R.id.tvMesa);
        numCartao = (TextView) findViewById(R.id.tvCartao);
        lista = (ListView) findViewById(R.id.listaPedidos);
        lista.setOnItemClickListener(onItemClickListener);

        renderizarComponentes();

        if (!Cardapio.getListaItensSelecionados().isEmpty()) {

            criarPedido();

        } else {

            mensagem(getString(R.string.nao_ha_itens_selecionados));
        }

        atualizarAdapter();

        salvarPedido.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO")) {

                    if (Cartao.getInstance().getNumeroCartao() == null) {

                        mensagem(getString(R.string.cartao_nao_selecionado));
                        return;
                    }
                }

                if (Mesa.getInstance().getNumeroMesa() == null) {
                    mensagem(getString(R.string.mesa_nao_selecionada));
                    return;
                }

                if(Pedido.getListaPedidos().isEmpty()){
                    mensagem(getString(R.string.não_ha_itens_para_salvar));
                    return;
                }

                ConverteJsonPedido converteJsonPedido = new ConverteJsonPedido();
                String json = converteJsonPedido.converteListaPedidoParaJson(Pedido.getListaPedidos());

                AsyncTaskSalvarPedido task = new AsyncTaskSalvarPedido();
                task.execute(json);
            }
        });
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            pedido = Pedido.getListaPedidos().get(i);
            Pedido.setInstance(pedido);
            abrirMenu(view);
        }
    };


    private class AsyncTaskSalvarPedido extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {

            dialog.setTitle(getString(R.string.salvando_pedidos));
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String strJson = strings[0];

            String metodoWs = "pedido/salvarPedidos";
            StringBuilder resultado;
            AndroidUtil androidUtil = new AndroidUtil();
            resultado = androidUtil.metodoPost(metodoWs, strJson);

            return resultado.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {

            mensagem(resultado);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            Cartao.setInstance(null);
            Mesa.setInstance(null);
            Cardapio.getListaItensSelecionados().clear();
            Pedido.getListaPedidos().clear();
            atualizarAdapter();

            intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void verificarPendencias() {

        if (Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO")) {

            if (Cartao.getInstance().getNumeroCartao() == null) {

                mensagem(getString(R.string.cartao_nao_selecionado));
                return;
            }
        }

        if (Mesa.getInstance().getNumeroMesa() == null) {
            mensagem(getString(R.string.mesa_nao_selecionada));
            return;
        }
    }

    private void criarPedido() {

        verificarPendencias();

        for (Quantidade qtde : Quantidade.getListaQuantidades()) {

            if (qtde.getQuantString().equalsIgnoreCase("1")) {
                quantidade = qtde;
                break;
            }
        }

        for (Cardapio itemCardapio : Cardapio.getListaItensSelecionados()) {

            pedido = new Pedido();
            pedido.setStatusPedido("PROCESSANDO");
            pedido.setFuncionario(Funcionario.getInstance());
            pedido.setItemCardapio(itemCardapio);
            pedido.setMesa(Mesa.getInstance());
            pedido.setCartao(Cartao.getInstance());
            pedido.setQuantidade(quantidade);
            pedido.setDataPedido(new Date());


            if (!Pedido.getListaPedidos().isEmpty()) {

                if (!Pedido.getListaPedidos().contains(pedido)) {
                    Pedido.getListaPedidos().add(pedido);
                }
            } else {

                Pedido.getListaPedidos().add(pedido);
            }
        }

        atualizarAdapter();
    }

    public void atualizarAdapter() {

        adapter = new NovoPedidoAdapter(
                NovoPedidoActivity.this, Pedido.getListaPedidos());
        lista.setAdapter(adapter);

    }

    private void renderizarComponentes() {

        if (Mesa.getInstance().getNumeroMesa() != null) {

            numMesa.setText(String.format(getResources().getString(R.string.string_mesa), Mesa.getInstance().getNumeroMesa()));

        } else {

            numMesa.setVisibility(View.GONE);
        }

        if (Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO") && Cartao.getInstance().getNumeroCartao() != null) {

            numCartao.setText(String.format(getResources().getString(R.string.string_cartao), Cartao.getInstance().getNumeroCartao()));

        } else {

            numCartao.setVisibility(View.GONE);
        }
    }

    public void abrirMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.inflate(R.menu.actions);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {


        if (item.getItemId() == R.id.act_cancelar_pedido) {

            adapter.cancelarPedido();
            Mesa.setInstance(null);
            Cartao.setInstance(null);
            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();
            return true;

        } else if (item.getItemId() == R.id.act_excluir_item) {

            adapter.excluirPedido(Pedido.getInstance());

            Cardapio.getListaItensSelecionados().clear();

            if(Pedido.getListaPedidos().size() <= 0){
                Cartao.setInstance(null);
                 Mesa.setInstance(null);
                Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent);
                finish();
            }

            return true;

        } else if (item.getItemId() == R.id.act_alterar_qtde) {

            Intent intent = new Intent(getApplicationContext(), QuantidadeActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return false;
    }

    public void mensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {

                intent = new Intent(getApplicationContext(),CardapioActivity.class);
                startActivity(intent);
                finish();
            }
}


