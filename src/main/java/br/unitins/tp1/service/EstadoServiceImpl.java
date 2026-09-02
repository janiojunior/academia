package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Estado;
import br.unitins.tp1.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Override
    @Transactional
    public Estado create(Estado estado) {
        repository.persist(estado);
        return estado;
    }

    @Override
    @Transactional
    public void update(Long id, Estado estado) {
        Estado estadoBanco = repository.findById(id);
        if (estadoBanco == null) {
            throw new RuntimeException("Estado não encontrado");
        }
        estadoBanco.setNome(estado.getNome());
        estadoBanco.setSigla(estado.getSigla());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Estado findById(Long id) {
       return repository.findById(id);
    }

    @Override
    public List<Estado> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Estado> findAll() {
        return repository.listAll();
    }
    
}
