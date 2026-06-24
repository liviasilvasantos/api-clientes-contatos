package com.nubank.clientescontatos.infrastructure.controller;

import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.infrastructure.controller.dto.ClienteRequestDTO;
import com.nubank.clientescontatos.infrastructure.controller.dto.ClienteResponseDTO;
import com.nubank.clientescontatos.infrastructure.controller.dto.ContatoResponseDTO;
import com.nubank.clientescontatos.usecase.CadastrarClienteUseCase;
import com.nubank.clientescontatos.usecase.ListarClientesUseCase;
import com.nubank.clientescontatos.usecase.ListarContatosPorClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes e seus contatos")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final ListarContatosPorClienteUseCase listarContatosPorClienteUseCase;

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente", description = "Cria um novo cliente no sistema. O e-mail do cliente deve ser único.")
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@Valid @RequestBody ClienteRequestDTO dto) {
        Cliente domain = dto.toDomain();
        Cliente salvo = cadastrarClienteUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.fromDomain(salvo));
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna a listagem de todos os clientes cadastrados juntamente com seus respectivos contatos.")
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> response = listarClientesUseCase.execute().stream()
                .map(ClienteResponseDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/contatos")
    @Operation(summary = "Listar contatos de um cliente específico", description = "Retorna a listagem de todos os contatos associados ao cliente com o ID especificado.")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorCliente(@PathVariable Long id) {
        List<ContatoResponseDTO> response = listarContatosPorClienteUseCase.execute(id).stream()
                .map(ContatoResponseDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
