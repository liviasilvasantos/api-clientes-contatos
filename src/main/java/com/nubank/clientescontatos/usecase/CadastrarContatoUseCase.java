package com.nubank.clientescontatos.usecase;

import com.nubank.clientescontatos.domain.exception.ResourceNotFoundException;
import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import com.nubank.clientescontatos.domain.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarContatoUseCase {

    private final ClienteRepository clienteRepository;
    private final ContatoRepository contatoRepository;

    public Contato execute(Long clienteId, Contato contato) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + clienteId));

        cliente.addContato(contato);
        return contatoRepository.save(contato);
    }
}
