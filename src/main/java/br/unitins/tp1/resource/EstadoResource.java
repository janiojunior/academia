package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Estado;
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
    public List<Estado> listar() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Estado buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/nome/{nome}")
    public List<Estado> buscarPorNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    public Estado inserir(Estado estado) {
        return service.create(estado);
    } 

    @PUT
    @Path("/{id}")
    public void atualizar(@PathParam("id") Long id, Estado estado) {
       service.update(id, estado);
    } 

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        service.delete(id);
    }


}
