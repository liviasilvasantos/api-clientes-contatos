package com.nubank.clientescontatos.infrastructure.persistence.gateway;

import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.domain.repository.ContatoRepository;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ContatoEntity;
import com.nubank.clientescontatos.infrastructure.persistence.mapper.ContatoEntityMapper;
import com.nubank.clientescontatos.infrastructure.persistence.repository.ContatoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContatoRepositoryGateway implements ContatoRepository {

    private final ContatoJpaRepository contatoJpaRepository;

    @Override
    public Contato save(Contato contato) {
        ContatoEntity entity = ContatoEntityMapper.toEntity(contato);
        ContatoEntity salvo = contatoJpaRepository.save(entity);
        return ContatoEntityMapper.toDomain(salvo);
    }

    @Override
    public List<Contato> findByClienteId(Long clienteId) {
        return contatoJpaRepository.findByClienteId(clienteId).stream()
                .map(ContatoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
