package br.unitins.tp1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PsicologoResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaPsicologos() {
        given()
                .when().get("/psicologos")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Fabiana Costa Melo"));

        given()
                .queryParam("nome", "FABiana")
                .when().get("/psicologos/search")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Fabiana Costa Melo"));
    }

    @Test
    void criarAtualizarEBuscarPsicologo() {
        Map<String, String> psicologo = dadosPsicologo("Psicologo");
        Long id = criarPsicologo(psicologo);

        try {
            Map<String, String> atualizado = dadosPsicologo("Psicologo atualizado");
            given()
                    .contentType("application/json")
                    .body(atualizado)
                    .when().put("/psicologos/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/psicologos/" + id)
                    .then()
                    .statusCode(200)
                    .body("nome", equalTo(atualizado.get("nome")))
                    .body("crp", equalTo(atualizado.get("crp")));
        } finally {
            excluirPsicologo(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        given()
                .contentType("application/json")
                .body(Map.of("nome", " ", "cpf", "123", "email", "x", "crp", ""))
                .when().post("/psicologos")
                .then()
                .statusCode(400);

        given()
                .when().get("/psicologos/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(dadosPsicologo("Psicologo inexistente"))
                .when().put("/psicologos/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/psicologos/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirPsicologoCriadoRetorna204() {
        Long id = criarPsicologo(dadosPsicologo("Psicologo para excluir"));

        given()
                .when().delete("/psicologos/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/psicologos/" + id)
                .then()
                .statusCode(404);
    }

    private Long criarPsicologo(Map<String, String> psicologo) {
        Object id = given()
                .contentType("application/json")
                .body(psicologo)
                .when().post("/psicologos")
                .then()
                .statusCode(201)
                .body("nome", equalTo(psicologo.get("nome")))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private Map<String, String> dadosPsicologo(String prefixo) {
        long unique = Math.abs(System.nanoTime());
        return Map.of(
                "nome", prefixo + " " + unique,
                "cpf", String.format("%011d", unique % 100_000_000_000L),
                "email", "psicologo" + unique + "@example.com",
                "crp", "CRP-" + unique);
    }

    private void excluirPsicologo(Long id) {
        given().when().delete("/psicologos/" + id);
    }
}