package br.edu.infnet.spring_boot.AT;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import br.edu.infnet.spring_boot.AT.service.FuncionarioService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// @WebMvcTest(FuncionarioController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestesAPI {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testGet() throws Exception {
        Funcionario funcionario = new Funcionario("Funcionario 1", "Endereço 1", "Telefone 1", "Email 1", "Data de Nascimento 1", null);
        funcionarioService.createFuncionario(funcionario).getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/funcionario")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testPost() throws Exception {
        Funcionario funcionario = new Funcionario("Funcionario 1", "Endereço 1", "Telefone 1", "Email 1", "Data de Nascimento 1", null);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/funcionario")
                .content(objectMapper.writeValueAsString(funcionario))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testPut() throws Exception {
        Funcionario funcionario1 = new Funcionario("Funcionario 1", "Endereço 1", "Telefone 1", "Email 1", "Data de Nascimento 1", null);
        Funcionario funcionario2 = new Funcionario("Funcionario 2", "Endereço 2", "Telefone 2", "Email 2", "Data de Nascimento 2", null);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/funcionario")
                .content(objectMapper.writeValueAsString(funcionario1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .put("/funcionario/1")
                .content(objectMapper.writeValueAsString(funcionario2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testDelete() throws Exception {
        Funcionario funcionario = new Funcionario("Funcionario 1", "Endereço 1", "Telefone 1", "Email 1", "Data de Nascimento 1", null);
        Long id = funcionarioService.createFuncionario(funcionario).getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/funcionario/" + id)).andDo(print()).andExpect(status().isAccepted());
    }
}