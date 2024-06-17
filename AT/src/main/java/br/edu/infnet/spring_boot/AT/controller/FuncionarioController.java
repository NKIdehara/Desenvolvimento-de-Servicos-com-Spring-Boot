package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import br.edu.infnet.spring_boot.AT.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    
    @GetMapping("/funcionario")
    public ResponseEntity<?> getFuncionarios(){
        if(funcionarioService.getFuncionarios().isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem funcionários!");
        return ResponseEntity.ok(funcionarioService.getFuncionarios());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<?> getFuncionarioById(@PathVariable Long id){
        if(funcionarioService.getFuncionarioById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não cadastrado!");
        return ResponseEntity.ok(funcionarioService.getFuncionarioById(id));
    }

    @GetMapping("/funcionario/departamento/{idDepartamento}")
    public ResponseEntity<?> getFuncionariosByDepartamentoId(@PathVariable Long idDepartamento) {
        if(funcionarioService.getFuncionarioById(idDepartamento).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem funcionários neste departamento!");
        return ResponseEntity.ok(funcionarioService.getFuncionariosByDepartamentoId(idDepartamento));
    }
    
    @PostMapping("/funcionario")
    public ResponseEntity<?> createcreateFuncionarioAluno(@RequestBody Funcionario funcionario) {
        if(funcionarioService.createFuncionario(funcionario) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível cadastrar Usuário!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário cadastrado!");
    }

    @PutMapping("funcionario/{id}")
    public ResponseEntity<?> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario){
        Funcionario update = funcionarioService.updateFuncionario(id, funcionario);
        if(update == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não cadastrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Funcionário atualizado!");        
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<?> deleteFuncionarioById(@PathVariable Long id){
        if(funcionarioService.getFuncionarioById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não cadastrado!");
        funcionarioService.deleteFuncionarioById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Funcionário excluído!");
    }
}
