package com.nubank.clientescontatos.domain.repository;

import com.nubank.clientescontatos.domain.model.Contato;

import java.util.List;

public interface ContatoRepository {
    Contato save(Contato contato);
    List<Contato> findByClienteId(Long clienteId);
}
