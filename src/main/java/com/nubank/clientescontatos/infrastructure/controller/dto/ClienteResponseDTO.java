package com.nubank.clientescontatos.infrastructure.controller.dto;

import com.nubank.clientescontatos.domain.model.Cliente;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String funcionalEmpresa;
    private LocalDateTime lastUpdate;
    private List<ContatoResponseDTO> contatos;

    public static ClienteResponseDTO fromDomain(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .funcionalEmpresa(cliente.getFuncionalEmpresa())
                .lastUpdate(cliente.getLastUpdate())
                .contatos(cliente.getContatos() != null ?
                        cliente.getContatos().stream()
                                .map(ContatoResponseDTO::fromDomain)
                                .collect(Collectors.toList()) :
                        List.of())
                .build();
    }
}
