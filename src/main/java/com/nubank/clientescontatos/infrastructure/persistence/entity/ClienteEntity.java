package com.nubank.clientescontatos.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "funcional_empresa")
    private String funcionalEmpresa;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ContatoEntity> contatos = new ArrayList<>();

    public void addContato(ContatoEntity contato) {
        contatos.add(contato);
        contato.setCliente(this);
    }

    public void removeContato(ContatoEntity contato) {
        contatos.remove(contato);
        contato.setCliente(null);
    }
}
