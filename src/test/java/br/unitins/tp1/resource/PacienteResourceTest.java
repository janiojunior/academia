package br.unitins.tp1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PacienteResourceTest {

    @Test
    void listarEBuscarPorNomeRetornaPacientes() {
        given()
                .when().get("/pacientes")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Ana Clara Souza"));

        given()
                .queryParam("nome", "ANA CLARA")
                .when().get("/pacientes/search")
                .then()
                .statusCode(200)
                .body("nome", hasItem("Ana Clara Souza"));
    }

    @Test
    void criarAtualizarEBuscarPaciente() {
        Map<String, String> paciente = dadosPaciente("Paciente");
        Long id = criarPaciente(paciente);

        try {
            Map<String, String> atualizado = dadosPaciente("Paciente atualizado");
            given()
                    .contentType("application/json")
                    .body(atualizado)
                    .when().put("/pacientes/" + id)
                    .then()
                    .statusCode(204);

            given()
                    .when().get("/pacientes/" + id)
                    .then()
                    .statusCode(200)
                    .body("nome", equalTo(atualizado.get("nome")))
                    .body("cpf", equalTo(atualizado.get("cpf")));
        } finally {
            excluirPaciente(id);
        }
    }

    @Test
    void rejeitarDadosInvalidosEIdsInexistentes() {
        given()
                .contentType("application/json")
                .body(Map.of("nome", "", "cpf", "123", "email", "x", "telefone", "", "endereco", ""))
                .when().post("/pacientes")
                .then()
                .statusCode(400);

        given()
                .when().get("/pacientes/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .contentType("application/json")
                .body(dadosPaciente("Paciente invalido"))
                .when().put("/pacientes/9223372036854775807")
                .then()
                .statusCode(404);

        given()
                .when().delete("/pacientes/9223372036854775807")
                .then()
                .statusCode(404);
    }

    @Test
    void excluirPacienteCriadoRetorna204() {
        Long id = criarPaciente(dadosPaciente("Paciente para excluir"));

        given()
                .when().delete("/pacientes/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/pacientes/" + id)
                .then()
                .statusCode(404);
    }

    private Long criarPaciente(Map<String, String> paciente) {
        Object id = given()
                .contentType("application/json")
                .body(paciente)
                .when().post("/pacientes")
                .then()
                .statusCode(201)
                .body("nome", equalTo(paciente.get("nome")))
                .extract()
                .path("id");
        return ((Number) id).longValue();
    }

    private Map<String, String> dadosPaciente(String prefixo) {
        long unique = Math.abs(System.nanoTime());
        return Map.of(
                "nome", prefixo + " " + unique,
                "cpf", String.format("%011d", unique % 100_000_000_000L),
                "email", "paciente" + unique + "@example.com",
                "telefone", "6399" + String.format("%07d", unique % 10_000_000L),
                "endereco", "Rua de teste numero " + unique);
    }

    private void excluirPaciente(Long id) {
        given().when().delete("/pacientes/" + id);
    }
}