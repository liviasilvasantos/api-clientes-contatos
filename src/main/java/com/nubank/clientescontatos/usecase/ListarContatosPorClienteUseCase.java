package com.nubank.clientescontatos.usecase;

import com.nubank.clientescontatos.domain.exception.ResourceNotFoundException;
import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import com.nubank.clientescontatos.domain.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListarContatosPorClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final ContatoRepository contatoRepository;

    public List<Contato> execute(Long clienteId) {
        if (!clienteRepository.findById(clienteId).isPresent()) {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + clienteId);
        }
        return contatoRepository.findByClienteId(clienteId);
    }
}
