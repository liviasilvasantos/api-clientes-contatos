package com.nubank.clientescontatos.domain.repository;

import com.nubank.clientescontatos.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    boolean existsByEmail(String email);
}
