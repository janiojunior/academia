package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Estado;
import br.unitins.tp1.model.Municipio;
import br.unitins.tp1.repository.EstadoRepository;
import br.unitins.tp1.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository repository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public Municipio create(Municipio municipio, Long idEstado) {
        municipio.setEstado(buscarEstado(idEstado));
        repository.persist(municipio);
        return municipio;
    }

    @Override
    @Transactional
    public void update(Long id, Municipio municipio, Long idEstado) {
        Municipio municipioExistente = repository.findById(id);
        if (municipioExistente == null) {
            throw new NotFoundException("Municipio nao encontrado.");
        }

        municipioExistente.setNome(municipio.getNome());
        municipioExistente.setEstado(buscarEstado(idEstado));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Municipio nao encontrado.");
        }
    }

    @Override
    public Municipio findById(Long id) {
        Municipio municipio = repository.findById(id);
        if (municipio == null) {
            throw new NotFoundException("Municipio nao encontrado.");
        }
        return municipio;
    }

    @Override
    public List<Municipio> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Municipio> findAll() {
        return repository.listAll();
    }

    private Estado buscarEstado(Long idEstado) {
        Estado estado = estadoRepository.findById(idEstado);
        if (estado == null) {
            throw new BadRequestException("Estado informado nao encontrado.");
        }
        return estado;
    }
}