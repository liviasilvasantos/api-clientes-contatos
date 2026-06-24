package com.nubank.clientescontatos.infrastructure.persistence.mapper;

import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ClienteEntity;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ContatoEntity;

public class ContatoEntityMapper {

    public static Contato toDomain(ContatoEntity entity) {
        if (entity == null) {
            return null;
        }
        return Contato.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .cliente(entity.getCliente() != null ? 
                    Cliente.builder()
                        .id(entity.getCliente().getId())
                        .nome(entity.getCliente().getNome())
                        .email(entity.getCliente().getEmail())
                        .telefone(entity.getCliente().getTelefone())
                        .build() : null)
                .build();
    }

    public static ContatoEntity toEntity(Contato domain) {
        if (domain == null) {
            return null;
        }
        return ContatoEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .email(domain.getEmail())
                .telefone(domain.getTelefone())
                .cliente(domain.getCliente() != null ?
                    ClienteEntity.builder()
                        .id(domain.getCliente().getId())
                        .nome(domain.getCliente().getNome())
                        .email(domain.getCliente().getEmail())
                        .telefone(domain.getCliente().getTelefone())
                        .build() : null)
                .build();
    }
}
