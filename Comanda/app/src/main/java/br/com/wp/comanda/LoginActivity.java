package br.com.wp.comanda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.wp.Util.AndroidUtil;
import br.com.wp.Util.CriptografarSenha;
import br.com.wp.json.ConverteJsonFuncionario;
import br.com.wp.json.ConverteJsonUsuario;
import br.com.wp.modelo.Configuracao;
import br.com.wp.modelo.Funcionario;
import br.com.wp.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    public Button logar;
    public Button limpar;
    private EditText edtEmail;
    private EditText edtSenha;
    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(getApplicationContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtSenha = (EditText) findViewById(R.id.edt_senha);
        logar = (Button) findViewById(R.id.btn_entrar);
        limpar = (Button) findViewById(R.id.btn_limpar);


        limpar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                edtEmail.setText("");
                edtSenha.setText("");
            }
        });

        logar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                email = String.valueOf(edtEmail.getText());
                senha = String.valueOf(edtSenha.getText());

                Usuario usuario = new Usuario();

                CriptografarSenha criptografarSenha = new CriptografarSenha();

                String senhaCriptografada = criptografarSenha.criptografarSenha(senha);

                usuario.setSenha(senhaCriptografada);

                Funcionario funcionario = new Funcionario();

                funcionario.setEmail(email);

                usuario.setFuncionario(funcionario);

                if(email.equals("") || senha.equals("")){

                   mensagem(getString(R.string.email_senha_sao_obrigatorios));

                    return;
                }


                ConverteJsonUsuario converteJsonUsuario = new ConverteJsonUsuario();

                String json = converteJsonUsuario.converteUsuarioParaJson(usuario);
                AsyncTaskLogin taskLogin = new AsyncTaskLogin();
                taskLogin.execute(json);
            }
        });
    }


    private class AsyncTaskLogin extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            dialog.setTitle(getString(R.string.autenticando_usuario));
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            String json = strings[0];
            String metodoWs = getString(R.string.usuario);
            StringBuilder resultado;
            AndroidUtil androidUtil = new AndroidUtil();

                resultado = androidUtil.metodoPost(metodoWs, json);

                return resultado.toString();
        }

            @Override
            protected void onPostExecute (String strRetorno){

                if(strRetorno.contains("{")){

                    ConverteJsonFuncionario converteJsonFuncionario = new ConverteJsonFuncionario();

                    Funcionario funcionario = converteJsonFuncionario.converteJsonParaFuncionario(strRetorno);

                    Funcionario.getInstance().setInstance(funcionario);

                    AsyncTaskConfiguracao asyncTaskConfiguracao = new AsyncTaskConfiguracao();
                    asyncTaskConfiguracao.execute();

                    mensagem(getString(R.string.usuario_conectado));

                } else {

                    mensagem(strRetorno);
                }

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }

        private class AsyncTaskConfiguracao extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {

                dialog.setTitle(getString(R.string.carregando_configuracoes));
                dialog.show();
            }

            @Override
            protected String doInBackground(Void... voids) {

                StringBuilder resultado;

                String metodoWs = "configuracao/buscarConfiguracao";

                AndroidUtil androidUtil = new AndroidUtil();

                resultado = androidUtil.metodoGet(metodoWs);

                return resultado.toString();
            }

            @Override
            protected void onPostExecute(String resultado) {

                if (!resultado.isEmpty()) {

                    Configuracao.getInstance().setTipoCobranca(resultado);

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                    finish();


                } else {

                    mensagem(getString(R.string.não_ha_config_definidas));
                }
            }
        }

        public void mensagem(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

