package br.unitins.tp1.dto;

import br.unitins.tp1.model.Paciente;

public record PacienteResponseDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String endereco) {

    public static PacienteResponseDTO fromEntity(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getId(),
            paciente.getNome(),
            paciente.getCpf(),
            paciente.getEmail(),
            paciente.getTelefone(),
            paciente.getEndereco());
    }
}