package com.nubank.clientescontatos.infrastructure.controller.dto;

import com.nubank.clientescontatos.domain.model.Contato;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContatoResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Long clienteId;

    public static ContatoResponseDTO fromDomain(Contato contato) {
        if (contato == null) {
            return null;
        }
        return ContatoResponseDTO.builder()
                .id(contato.getId())
                .nome(contato.getNome())
                .email(contato.getEmail())
                .telefone(contato.getTelefone())
                .clienteId(contato.getCliente() != null ? contato.getCliente().getId() : null)
                .build();
    }
}
