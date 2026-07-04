package com.nubank.clientescontatos.infrastructure.persistence.mapper;

import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ClienteEntity;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ContatoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteEntityMapper {

    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }

        Cliente domain = Cliente.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .funcionalEmpresa(entity.getFuncionalEmpresa())
                .build();

        if (entity.getContatos() != null) {
            List<Contato> contatos = entity.getContatos().stream()
                    .map(contatoEntity -> Contato.builder()
                            .id(contatoEntity.getId())
                            .nome(contatoEntity.getNome())
                            .email(contatoEntity.getEmail())
                            .telefone(contatoEntity.getTelefone())
                            .cliente(domain)
                            .build())
                    .collect(Collectors.toList());
            domain.setContatos(contatos);
        }

        return domain;
    }

    public static ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }

        ClienteEntity entity = ClienteEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .email(domain.getEmail())
                .telefone(domain.getTelefone())
                .funcionalEmpresa(domain.getFuncionalEmpresa())
                .build();

        if (domain.getContatos() != null) {
            List<ContatoEntity> contatos = domain.getContatos().stream()
                    .map(contato -> ContatoEntity.builder()
                            .id(contato.getId())
                            .nome(contato.getNome())
                            .email(contato.getEmail())
                            .telefone(contato.getTelefone())
                            .cliente(entity)
                            .build())
                    .collect(Collectors.toList());
            entity.setContatos(contatos);
        }

        return entity;
    }
}
