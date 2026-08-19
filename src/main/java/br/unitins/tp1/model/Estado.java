package br.unitins.tp1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Estado extends PanacheEntity {
    public String nome;
    public String sigla;  
}
