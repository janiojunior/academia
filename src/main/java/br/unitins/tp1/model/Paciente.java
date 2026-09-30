package br.unitins.tp1.model;

import jakarta.persistence.Entity;

@Entity 
public class Paciente extends Pessoa {

    private String telefone;
    private String endereco;

    public Paciente() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
