package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.EstadoDTO;
import br.unitins.tp1.dto.EstadoResponseDTO;
import br.unitins.tp1.model.Estado;
import br.unitins.tp1.model.Regiao;
import br.unitins.tp1.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService service;

    @GET
    public List<EstadoResponseDTO> listar() {
        return service.findAll().stream()
                .map(EstadoResponseDTO::fromEntity)
                .toList();

        // mesmo codigo acima
        // return service.findAll().stream().map(e ->
        // EstadoResponseDTO.fromEntity(e)).toList();
    }

    @GET
    @Path("/{id}")
    public EstadoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return EstadoResponseDTO.fromEntity(service.findById(id));
    }

    @GET
    @Path("/nome/{nome}")
    public List<EstadoResponseDTO> buscarPorNome(@PathParam("nome") String nome) {
        return service.findByNome(nome).stream().map(EstadoResponseDTO::fromEntity).toList();
    }

    @POST
    public EstadoResponseDTO inserir(EstadoDTO dto) {
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estado.setRegiao(Regiao.fromId(dto.idRegiao()));
        return EstadoResponseDTO.fromEntity(service.create(estado));
    }

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, EstadoDTO dto) {
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estado.setRegiao(Regiao.fromId(dto.idRegiao()));

        service.update(id, estado);

    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        service.delete(id);
    }

}
