package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Psicologo;

public interface PsicologoService {
    Psicologo create(Psicologo psicologo);
    void update(Long id, Psicologo psicologo);
    void delete(Long id);
    Psicologo findById(Long id);
    List<Psicologo> findByNome(String nome);
    List<Psicologo> findAll();
}