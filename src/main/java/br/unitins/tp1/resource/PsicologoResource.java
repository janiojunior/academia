package br.unitins.tp1.resource;

import br.unitins.tp1.dto.PsicologoDTO;
import br.unitins.tp1.dto.PsicologoResponseDTO;
import br.unitins.tp1.model.Psicologo;
import br.unitins.tp1.service.PsicologoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/psicologos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PsicologoResource {

    @Inject
    PsicologoService service;

    @GET
    public Response listar() {
        return Response.ok(service.findAll().stream()
                .map(PsicologoResponseDTO::fromEntity)
                .toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(PsicologoResponseDTO.fromEntity(service.findById(id))).build();
    }

    @GET
    @Path("/search")
    public Response buscarPorNome(@QueryParam("nome") String nome) {
        return Response.ok(service.findByNome(nome).stream()
                .map(PsicologoResponseDTO::fromEntity)
                .toList()).build();
    }

    @POST
    public Response inserir(@Valid PsicologoDTO dto) {
        Psicologo psicologo = new Psicologo();
        psicologo.setNome(dto.nome());
        psicologo.setCpf(dto.cpf());
        psicologo.setEmail(dto.email());
        psicologo.setCrp(dto.crp());
        return Response.status(Response.Status.CREATED)
                .entity(PsicologoResponseDTO.fromEntity(service.create(psicologo)))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, @Valid PsicologoDTO dto) {
        Psicologo psicologo = new Psicologo();
        psicologo.setNome(dto.nome());
        psicologo.setCpf(dto.cpf());
        psicologo.setEmail(dto.email());
        psicologo.setCrp(dto.crp());
        service.update(id, psicologo);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}