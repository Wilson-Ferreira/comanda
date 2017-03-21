package br.com.wp.modelo;

import java.io.Serializable;

/**
 * Created by Wilson F Florindo
 */

public class Usuario implements Serializable {


    private static Long id;
    private String email;
    private String senha;
    private Funcionario funcionario;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        Usuario.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
