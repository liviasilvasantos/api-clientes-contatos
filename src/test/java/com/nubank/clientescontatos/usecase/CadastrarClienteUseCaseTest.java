package com.nubank.clientescontatos.usecase;

import com.nubank.clientescontatos.domain.exception.EmailAlreadyExistsException;
import com.nubank.clientescontatos.domain.model.Cliente;
import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarClienteUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = Cliente.builder()
                .nome("João")
                .email("joao@email.com")
                .telefone("11988887777")
                .build();
    }

    @Test
    void execute_ComSucesso_DeveSalvarCliente() {
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = cadastrarClienteUseCase.execute(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getEmail(), resultado.getEmail());
        verify(clienteRepository, times(1)).existsByEmail(cliente.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void execute_ComEmailDuplicado_DeveLancarExcecao() {
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            cadastrarClienteUseCase.execute(cliente);
        });

        verify(clienteRepository, times(1)).existsByEmail(cliente.getEmail());
        verify(clienteRepository, never()).save(any());
    }
}
