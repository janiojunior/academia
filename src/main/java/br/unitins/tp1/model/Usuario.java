package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Usuario extends DefaultEntity {
    private String login;
    private String senha;

    @JoinColumn(name = "id_pessoa", nullable = false)
    @ManyToOne 
    private Pessoa pessoa;
    
    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
