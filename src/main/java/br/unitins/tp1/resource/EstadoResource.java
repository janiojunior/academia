package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Estado;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estado")
public class EstadoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> listAll() {
        return Estado.listAll();
    }
    
}
