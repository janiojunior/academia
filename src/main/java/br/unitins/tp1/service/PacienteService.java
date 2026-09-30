package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Paciente;

public interface PacienteService {
    Paciente create(Paciente paciente);
    void update(Long id, Paciente paciente);
    void delete(Long id);
    Paciente findById(Long id);
    List<Paciente> findByNome(String nome);
    List<Paciente> findAll();
}