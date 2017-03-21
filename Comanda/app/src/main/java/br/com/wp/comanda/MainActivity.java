package br.com.wp.comanda;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import br.com.wp.modelo.Cardapio;
import br.com.wp.modelo.Cartao;
import br.com.wp.modelo.Configuracao;
import br.com.wp.modelo.Mesa;
import br.com.wp.modelo.Pedido;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressDialog dialog = new ProgressDialog(getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (Configuracao.getInstance().getTipoCobranca() != null) {

            if (!Configuracao.getInstance().getTipoCobranca().equalsIgnoreCase("CARTÃO")) {


                MenuItem item = menu.findItem(R.id.cartao);

                item.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.principal) {

            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.categoria) {

            Intent intent = new Intent(getApplicationContext(), CategoriaActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.cardapio) {

            Intent intent = new Intent(getApplicationContext(), CardapioActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.mesa) {

            Intent intent = new Intent(getApplicationContext(), MesaActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.cartao) {
            Intent intent = new Intent(getApplicationContext(), CartaoActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.categorias) {

            Intent intent = new Intent(getApplicationContext(), CategoriaActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.novoPedido) {
            Intent intent = new Intent(getApplicationContext(), NovoPedidoActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.act_cancelar_pedido) {

            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intent);
            finish();

            Cardapio.getListaItensSelecionados().clear();
            Pedido.getListaPedidos().clear();
            Mesa.setInstance(null);
            Cartao.setInstance(null);
            return true;
        }

        if (id == R.id.login) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            Configuracao.getInstance().setTipoCobranca(null);
            Cardapio.getListaItensSelecionados().clear();
            Pedido.getListaPedidos().clear();
            Mesa.setInstance(null);
            Cartao.setInstance(null);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

