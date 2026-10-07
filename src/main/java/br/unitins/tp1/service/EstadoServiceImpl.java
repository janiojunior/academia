package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.exception.BusinessValidationException;
import br.unitins.tp1.exception.ViolationDetail;
import br.unitins.tp1.model.Estado;
import br.unitins.tp1.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Override
    @Transactional
    public Estado create(Estado estado) {
        validarSiglaDuplicada(estado.getSigla(), null);
        repository.persist(estado);
        return estado;
    }

    @Override
    @Transactional
    public void update(Long id, Estado estado) {
        Estado novoEstado = repository.findById(id);
        if (novoEstado == null) {
            throw new NotFoundException("Estado nao encontrado.");
        }
        validarSiglaDuplicada(estado.getSigla(), id);
        novoEstado.setNome(estado.getNome());
        novoEstado.setSigla(estado.getSigla());
        novoEstado.setRegiao(estado.getRegiao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Estado nao encontrado.");
        }
    }

    @Override
    public Estado findById(Long id) {
       Estado estado = repository.findById(id);
       if (estado == null) {
            throw new NotFoundException("Estado nao encontrado.");
       }
       return estado;
    }

    @Override
    public List<Estado> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Estado> findAll() {
        return repository.listAll();
    }

    private void validarSiglaDuplicada(String sigla, Long idIgnorado) {
        boolean siglaDuplicada = idIgnorado == null
                ? repository.existsBySigla(sigla)
                : repository.existsBySiglaAndIdNot(sigla, idIgnorado);

        if (siglaDuplicada) {
            throw new BusinessValidationException(
                    Response.Status.CONFLICT,
                    "Um ou mais campos da requisicao sao invalidos.",
                    List.of(new ViolationDetail("sigla", "Ja existe um estado cadastrado com a sigla informada.")));
        }
    }
    
}
