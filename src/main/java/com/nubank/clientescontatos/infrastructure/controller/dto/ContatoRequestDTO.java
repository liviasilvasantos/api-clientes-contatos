package com.nubank.clientescontatos.infrastructure.controller.dto;

import com.nubank.clientescontatos.domain.model.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContatoRequestDTO {

    @NotBlank(message = "O nome do contato é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail do contato é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "O telefone do contato é obrigatório")
    @Size(min = 8, max = 20, message = "O telefone deve ter entre 8 e 20 caracteres")
    private String telefone;

    @NotNull(message = "O ID do cliente associado é obrigatório")
    private Long clienteId;

    public Contato toDomain() {
        return Contato.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .build();
    }
}
