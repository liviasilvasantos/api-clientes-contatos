package com.nubank.clientescontatos.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String funcionalEmpresa;
    private LocalDateTime lastUpdate;
    
    @Builder.Default
    private List<Contato> contatos = new ArrayList<>();

    public void addContato(Contato contato) {
        contatos.add(contato);
        contato.setCliente(this);
    }

    public void removeContato(Contato contato) {
        contatos.remove(contato);
        contato.setCliente(null);
    }
}
