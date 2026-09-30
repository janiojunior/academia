package br.unitins.tp1.repository;

import java.util.List;

import br.unitins.tp1.model.Psicologo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PsicologoRepository implements PanacheRepository<Psicologo> {

    public List<Psicologo> findByNome(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }
}