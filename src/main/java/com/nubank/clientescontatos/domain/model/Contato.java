package com.nubank.clientescontatos.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contato {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Cliente cliente;
}
