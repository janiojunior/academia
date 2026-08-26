package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Estado;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
public class EstadoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> listar() {
        return Estado.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estado buscarPorId(@PathParam("id") Long id) {
        return Estado.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Estado criar(Estado estado) {
        estado.persist();
        return estado;
    } 
}
