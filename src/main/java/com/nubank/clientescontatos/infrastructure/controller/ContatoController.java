package com.nubank.clientescontatos.infrastructure.controller;

import com.nubank.clientescontatos.domain.model.Contato;
import com.nubank.clientescontatos.infrastructure.controller.dto.ContatoRequestDTO;
import com.nubank.clientescontatos.infrastructure.controller.dto.ContatoResponseDTO;
import com.nubank.clientescontatos.usecase.CadastrarContatoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
@Tag(name = "Contatos", description = "Endpoints para gerenciamento de contatos")
public class ContatoController {

    private final CadastrarContatoUseCase cadastrarContatoUseCase;

    @PostMapping
    @Operation(summary = "Cadastrar um novo contato", description = "Cria um novo contato associado a um cliente existente.")
    public ResponseEntity<ContatoResponseDTO> cadastrarContato(@Valid @RequestBody ContatoRequestDTO dto) {
        Contato domain = dto.toDomain();
        Contato salvo = cadastrarContatoUseCase.execute(dto.getClienteId(), domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContatoResponseDTO.fromDomain(salvo));
    }
}
