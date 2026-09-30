package br.unitins.tp1.model;

import jakarta.persistence.Entity;

@Entity 
public class Psicologo extends Pessoa {
    private String crp;

    public Psicologo() {
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }
    
}
