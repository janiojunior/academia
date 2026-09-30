package br.unitins.tp1.dto;

import br.unitins.tp1.model.Psicologo;

public record PsicologoResponseDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String crp) {

    public static PsicologoResponseDTO fromEntity(Psicologo psicologo) {
        return new PsicologoResponseDTO(
            psicologo.getId(),
            psicologo.getNome(),
            psicologo.getCpf(),
            psicologo.getEmail(),
            psicologo.getCrp());
    }
}