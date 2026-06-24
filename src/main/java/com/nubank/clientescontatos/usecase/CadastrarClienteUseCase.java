package com.nubank.clientescontatos.usecase;

import com.nubank.clientescontatos.domain.exception.EmailAlreadyExistsException;
import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public Cliente execute(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailAlreadyExistsException("Já existe um cliente cadastrado com o e-mail: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }
}
