package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Psicologo;
import br.unitins.tp1.repository.PsicologoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PsicologoServiceImpl implements PsicologoService {

    @Inject
    PsicologoRepository repository;

    @Override
    @Transactional
    public Psicologo create(Psicologo psicologo) {
        repository.persist(psicologo);
        return psicologo;
    }

    @Override
    @Transactional
    public void update(Long id, Psicologo psicologo) {
        Psicologo novoPsicologo = repository.findById(id);
        if (novoPsicologo == null) {
            throw new NotFoundException("Psicologo nao encontrado.");
        }
        novoPsicologo.setNome(psicologo.getNome());
        novoPsicologo.setCpf(psicologo.getCpf());
        novoPsicologo.setEmail(psicologo.getEmail());
        novoPsicologo.setCrp(psicologo.getCrp());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Psicologo nao encontrado.");
        }
    }

    @Override
    public Psicologo findById(Long id) {
        Psicologo psicologo = repository.findById(id);
        if (psicologo == null) {
            throw new NotFoundException("Psicologo nao encontrado.");
        }
        return psicologo;
    }

    @Override
    public List<Psicologo> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Psicologo> findAll() {
        return repository.listAll();
    }
}