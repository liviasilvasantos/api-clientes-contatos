package com.nubank.clientescontatos.infrastructure.controller.dto;

import com.nubank.clientescontatos.domain.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDTO {

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail do cliente é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "O telefone do cliente é obrigatório")
    @Size(min = 8, max = 20, message = "O telefone deve ter entre 8 e 20 caracteres")
    private String telefone;

    private String funcionalEmpresa;

    public Cliente toDomain() {
        return Cliente.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .funcionalEmpresa(this.funcionalEmpresa)
                .build();
    }
}
