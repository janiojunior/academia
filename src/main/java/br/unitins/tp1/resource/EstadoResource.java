package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Estado;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Estado inserir(Estado estado) {
        estado.persist();
        return estado;
    } 

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void atualizar(@PathParam("id") Long id, Estado estado) {
        Estado estadoBanco = Estado.findById(id);
        if (estadoBanco == null) {
            throw new RuntimeException("Estado não encontrado");
        }
        estadoBanco.nome = estado.nome;
        estadoBanco.sigla = estado.sigla;
    } 

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluir(@PathParam("id") Long id) {
        Estado.deleteById(id);
    }


}
