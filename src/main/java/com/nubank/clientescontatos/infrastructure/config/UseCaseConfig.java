package com.nubank.clientescontatos.infrastructure.config;

import com.nubank.clientescontatos.domain.repository.ClienteRepository;
import com.nubank.clientescontatos.domain.repository.ContatoRepository;
import com.nubank.clientescontatos.usecase.CadastrarClienteUseCase;
import com.nubank.clientescontatos.usecase.CadastrarContatoUseCase;
import com.nubank.clientescontatos.usecase.ListarClientesUseCase;
import com.nubank.clientescontatos.usecase.ListarContatosPorClienteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(ClienteRepository clienteRepository) {
        return new CadastrarClienteUseCase(clienteRepository);
    }

    @Bean
    public ListarClientesUseCase listarClientesUseCase(ClienteRepository clienteRepository) {
        return new ListarClientesUseCase(clienteRepository);
    }

    @Bean
    public CadastrarContatoUseCase cadastrarContatoUseCase(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        return new CadastrarContatoUseCase(clienteRepository, contatoRepository);
    }

    @Bean
    public ListarContatosPorClienteUseCase listarContatosPorClienteUseCase(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        return new ListarContatosPorClienteUseCase(clienteRepository, contatoRepository);
    }
}
