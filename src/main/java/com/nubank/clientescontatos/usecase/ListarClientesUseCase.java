package com.nubank.clientescontatos.usecase;

import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarClientesUseCase {

    private final ClienteRepository clienteRepository;

    public List<Cliente> execute() {
        return clienteRepository.findAll();
    }
}
