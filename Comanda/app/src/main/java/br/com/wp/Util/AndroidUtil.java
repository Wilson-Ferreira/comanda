package br.com.wp.Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.wp.modelo.Configuracao;

/**
 * Created by User on 28/12/2016.
 */

public class AndroidUtil {

    private StringBuilder resultado = new StringBuilder();
    private HttpURLConnection conn;

    public StringBuilder metodoGet(String nomeMetodoNoWs){

        try {

            URL url = new URL(Configuracao.getUrl()+nomeMetodoNoWs);
            conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                resultado.append(inputLine);

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            //e.printStackTrace();

        } finally {

            if (conn != null) {
                conn.disconnect();
            }
        }

        return resultado;
    }

    public StringBuilder metodoPost(String nomeMetodoNoWs, String json){

        try {

            String url = Configuracao.getUrl()+nomeMetodoNoWs;

            URL obj = new URL(url);
            conn = (HttpURLConnection) obj.openConnection();

            conn.setConnectTimeout(4000);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                resultado.append(inputLine);
            }

            in.close();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            resultado.append("Sem conexão com servidor");
            return resultado;

        } finally {

            if (conn != null) {
                conn.disconnect();
            }
        }

        return resultado;
    }
}
