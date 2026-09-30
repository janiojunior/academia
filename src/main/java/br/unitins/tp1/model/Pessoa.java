package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity 
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
public class Pessoa extends DefaultEntity {
    private String nome;
    private String cpf;
    private String email;

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
