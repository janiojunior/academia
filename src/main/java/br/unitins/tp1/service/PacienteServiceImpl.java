package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Paciente;
import br.unitins.tp1.repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PacienteServiceImpl implements PacienteService {

    @Inject
    PacienteRepository repository;

    @Override
    @Transactional
    public Paciente create(Paciente paciente) {
        repository.persist(paciente);
        return paciente;
    }

    @Override
    @Transactional
    public void update(Long id, Paciente paciente) {
        Paciente novoPaciente = repository.findById(id);
        if (novoPaciente == null) {
            throw new NotFoundException("Paciente nao encontrado.");
        }
        novoPaciente.setNome(paciente.getNome());
        novoPaciente.setCpf(paciente.getCpf());
        novoPaciente.setEmail(paciente.getEmail());
        novoPaciente.setTelefone(paciente.getTelefone());
        novoPaciente.setEndereco(paciente.getEndereco());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Paciente nao encontrado.");
        }
    }

    @Override
    public Paciente findById(Long id) {
        Paciente paciente = repository.findById(id);
        if (paciente == null) {
            throw new NotFoundException("Paciente nao encontrado.");
        }
        return paciente;
    }

    @Override
    public List<Paciente> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Paciente> findAll() {
        return repository.listAll();
    }
}