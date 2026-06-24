package com.nubank.clientescontatos.infrastructure.persistence.repository;

import com.nubank.clientescontatos.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    boolean existsByEmail(String email);

    @Override
    @EntityGraph(attributePaths = {"contatos"})
    List<ClienteEntity> findAll();
}
