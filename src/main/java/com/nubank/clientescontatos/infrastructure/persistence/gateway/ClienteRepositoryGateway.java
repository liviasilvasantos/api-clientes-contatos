package com.nubank.clientescontatos.infrastructure.persistence.gateway;

import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ClienteEntity;
import com.nubank.clientescontatos.infrastructure.persistence.mapper.ClienteEntityMapper;
import com.nubank.clientescontatos.infrastructure.persistence.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryGateway implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = ClienteEntityMapper.toEntity(cliente);
        ClienteEntity salvo = clienteJpaRepository.save(entity);
        return ClienteEntityMapper.toDomain(salvo);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteJpaRepository.findAll().stream()
                .map(ClienteEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id)
                .map(ClienteEntityMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clienteJpaRepository.existsByEmail(email);
    }
}
