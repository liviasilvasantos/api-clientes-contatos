package com.nubank.clientescontatos.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.clientescontatos.infrastructure.controller.dto.ClienteRequestDTO;
import com.nubank.clientescontatos.infrastructure.controller.dto.ContatoRequestDTO;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ClienteEntity;
import com.nubank.clientescontatos.infrastructure.persistence.entity.ContatoEntity;
import com.nubank.clientescontatos.infrastructure.persistence.repository.ClienteJpaRepository;
import com.nubank.clientescontatos.infrastructure.persistence.repository.ContatoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteJpaRepository clienteRepository;

    @Autowired
    private ContatoJpaRepository contatoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ClienteEntity clienteSalvo;

    @BeforeEach
    void setUp() {
        contatoRepository.deleteAll();
        clienteRepository.deleteAll();

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Maria Silva")
                .email("maria@email.com")
                .telefone("11988888888")
                .build();

        ContatoEntity contato = ContatoEntity.builder()
                .nome("João Filho")
                .email("joao@email.com")
                .telefone("11977777777")
                .build();
        cliente.addContato(contato);

        clienteSalvo = clienteRepository.save(cliente);
    }

    @Test
    void cadastrarCliente_ComDadosValidos_DeveRetornarCreated() throws Exception {
        ClienteRequestDTO request = ClienteRequestDTO.builder()
                .nome("José Santos")
                .email("jose@email.com")
                .telefone("11966666666")
                .build();

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nome", is("José Santos")))
                .andExpect(jsonPath("$.email", is("jose@email.com")))
                .andExpect(jsonPath("$.telefone", is("11966666666")));
    }

    @Test
    void cadastrarCliente_ComEmailDuplicado_DeveRetornarConflict() throws Exception {
        ClienteRequestDTO request = ClienteRequestDTO.builder()
                .nome("Maria Outra")
                .email("maria@email.com") // duplicate email
                .telefone("11966666666")
                .build();

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error", is("Conflito de Informação")))
                .andExpect(jsonPath("$.message", containsString("Já existe um cliente cadastrado com o e-mail")));
    }

    @Test
    void cadastrarCliente_ComDadosInvalidos_DeveRetornarBadRequest() throws Exception {
        ClienteRequestDTO request = ClienteRequestDTO.builder()
                .nome("") // invalid name
                .email("email_invalido") // invalid email format
                .telefone("") // invalid phone
                .build();

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Erro de Validação")))
                .andExpect(jsonPath("$.errors", hasSize(5)));
    }

    @Test
    void listarTodos_DeveRetornarListaDeClientesComContatos() throws Exception {
        mockMvc.perform(get("/clientes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("Maria Silva")))
                .andExpect(jsonPath("$[0].contatos", hasSize(1)))
                .andExpect(jsonPath("$[0].contatos[0].nome", is("João Filho")));
    }

    @Test
    void cadastrarContato_ComDadosValidos_DeveRetornarCreated() throws Exception {
        ContatoRequestDTO request = ContatoRequestDTO.builder()
                .nome("Carlos Amigo")
                .email("carlos@email.com")
                .telefone("11955555555")
                .clienteId(clienteSalvo.getId())
                .build();

        mockMvc.perform(post("/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nome", is("Carlos Amigo")))
                .andExpect(jsonPath("$.clienteId", is(clienteSalvo.getId().intValue())));
    }

    @Test
    void cadastrarContato_ComClienteInexistente_DeveRetornarNotFound() throws Exception {
        ContatoRequestDTO request = ContatoRequestDTO.builder()
                .nome("Carlos Amigo")
                .email("carlos@email.com")
                .telefone("11955555555")
                .clienteId(999L) // non-existent client ID
                .build();

        mockMvc.perform(post("/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Recurso Não Encontrado")))
                .andExpect(jsonPath("$.message", containsString("Cliente não encontrado com o ID: 999")));
    }

    @Test
    void listarContatosPorCliente_DeveRetornarContatos() throws Exception {
        mockMvc.perform(get("/clientes/{id}/contatos", clienteSalvo.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome", is("João Filho")))
                .andExpect(jsonPath("$[0].clienteId", is(clienteSalvo.getId().intValue())));
    }

    @Test
    void listarContatosPorCliente_ComClienteInexistente_DeveRetornarNotFound() throws Exception {
        mockMvc.perform(get("/clientes/{id}/contatos", 999L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Recurso Não Encontrado")))
                .andExpect(jsonPath("$.message", containsString("Cliente não encontrado com o ID: 999")));
    }
}
