package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Municipio;

public interface MunicipioService {
    Municipio create(Municipio municipio, Long idEstado);
    void update(Long id, Municipio municipio, Long idEstado);
    void delete(Long id);
    Municipio findById(Long id);
    List<Municipio> findByNome(String nome);
    List<Municipio> findAll();
}