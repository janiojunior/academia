package br.unitins.tp1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class MunicipioResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaMunicipiosEEstado() {
        given()
                .when().get("/municipios")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Palmas"))
                .body("find { it.nome == 'Palmas' }.estado.sigla", equalTo("TO"));

        given()
                .queryParam("nome", "ARAG")
                .when().get("/municipios/search")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Araguaina"));
    }

    @Test
    void buscarPorIdInexistenteRetorna404() {
        given()
                .when().get("/municipios/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void criarMunicipioRetorna201ComEstadoAssociado() {
        Long idEstado = buscarIdEstadoTocantins();
        Long idMunicipio = criarMunicipio(nomeUnico("Municipio criado"), idEstado);

        try {
            given()
                    .when().get("/municipios/" + idMunicipio)
                    .then()
                    .statusCode(200)
                    .body("estado.id", equalTo(idEstado.intValue()));
        } finally {
            excluirMunicipio(idMunicipio);
        }
    }

    @Test
    void criarMunicipioInvalidoOuComEstadoInexistenteRetorna400() {
        Long idEstado = buscarIdEstadoTocantins();

        given()
                .contentType("application/json")
                .body(Map.of("nome", " ", "idEstado", idEstado))
                .when().post("/municipios")
                .then()
                .statusCode(400);

        Map<String, Object> estadoAusente = new HashMap<>();
        estadoAusente.put("nome", nomeUnico("Sem estado"));
        given()
                .contentType("application/json")
                .body(estadoAusente)
                .when().post("/municipios")
                .then()
                .statusCode(400);

        given()
                .contentType("application/json")
                .body(Map.of("nome", nomeUnico("Estado desconhecido"), "idEstado", Long.MAX_VALUE))
                .when().post("/municipios")
                .then()
                .statusCode(400);
    }

    @Test
    void atualizarEExcluirMunicipioRetorna204() {
        Long idEstado = buscarIdEstadoTocantins();
        Long idMunicipio = criarMunicipio(nomeUnico("Municipio original"), idEstado);
        String nomeAtualizado = nomeUnico("Municipio atualizado");

        try {
            given()
                    .contentType("application/json")
                    .body(Map.of("nome", nomeAtualizado, "idEstado", idEstado))
                    .when().put("/municipios/" + idMunicipio)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/municipios/" + idMunicipio)
                    .then()
                    .statusCode(200)
                    .body("nome", equalTo(nomeAtualizado));

            given()
                    .when().delete("/municipios/" + idMunicipio)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/municipios/" + idMunicipio)
                    .then()
                    .statusCode(404);
        } finally {
            excluirMunicipio(idMunicipio);
        }
    }

    @Test
    void atualizarEExcluirMunicipioInexistenteRetorna404() {
        Long idEstado = buscarIdEstadoTocantins();
        long idInexistente = Long.MAX_VALUE;

        given()
                .contentType("application/json")
                .body(Map.of("nome", "Municipio valido", "idEstado", idEstado))
                .when().put("/municipios/" + idInexistente)
                .then()
                .statusCode(404);

        given()
                .when().delete("/municipios/" + idInexistente)
                .then()
                .statusCode(404);
    }

    private Long buscarIdEstadoTocantins() {
        Object id = given()
                .queryParam("nome", "Tocantins")
                .when().get("/estados/search")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].id");
        return ((Number) id).longValue();
    }

    private Long criarMunicipio(String nome, Long idEstado) {
        Object id = given()
                .contentType("application/json")
                .body(Map.of("nome", nome, "idEstado", idEstado))
                .when().post("/municipios")
                .then()
                .statusCode(201)
                .body("nome", equalTo(nome))
                .body("estado.sigla", equalTo("TO"))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private void excluirMunicipio(Long idMunicipio) {
        given().when().delete("/municipios/" + idMunicipio);
    }

    private String nomeUnico(String prefixo) {
        return prefixo + " " + System.nanoTime();
    }
}