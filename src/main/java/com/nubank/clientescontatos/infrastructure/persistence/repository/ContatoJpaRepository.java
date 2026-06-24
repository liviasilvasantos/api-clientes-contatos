package com.nubank.clientescontatos.infrastructure.persistence.repository;

import com.nubank.clientescontatos.infrastructure.persistence.entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoJpaRepository extends JpaRepository<ContatoEntity, Long> {
    List<ContatoEntity> findByClienteId(Long clienteId);
}
