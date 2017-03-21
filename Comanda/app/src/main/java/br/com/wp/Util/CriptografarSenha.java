package br.com.wp.Util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 10/01/2017.
 */

public class CriptografarSenha {

    String senhaCriptografada;

    public String criptografarSenha(String senha){

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte byteHash[] = null;
            try {
                byteHash = messageDigest.digest(senha.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CriptografarSenha.class.getName()).log(Level.SEVERE, null, ex);
            }

            StringBuilder hexHash = new StringBuilder();
            for (byte b : byteHash) {
                hexHash.append(String.format("%02X", 0xFF & b));
            }

            senhaCriptografada = hexHash.toString();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografarSenha.class.getName()).log(Level.SEVERE, null, ex);
        }

        return senhaCriptografada;
    }
}
