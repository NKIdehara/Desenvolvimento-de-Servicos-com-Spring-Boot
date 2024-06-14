package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import br.edu.infnet.spring_boot.AT.service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
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
        return funcionarioService.getFuncionarios();
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<?> getFuncionarioById(@PathVariable Long id){
        return funcionarioService.getFuncionarioById(id);
    }

    @GetMapping("/funcionario/departamento/{idDepartamento}")
    public ResponseEntity<?> getFuncionariosByDepartamentoId(@PathVariable Long idDepartamento) {
        return funcionarioService.getFuncionariosByDepartamentoId(idDepartamento);
    }
    
    @PostMapping("/funcionario")
    public ResponseEntity<?> createcreateFuncionarioAluno(@RequestBody Funcionario funcionario) {
        return funcionarioService.createFuncionario(funcionario);
    }

    @PutMapping("funcionario/{id}")
    public ResponseEntity<?> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario){
        return funcionarioService.updateFuncionario(id, funcionario);
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<?> deleteFuncionarioById(@PathVariable Long id){
        return funcionarioService.deleteFuncionarioById(id);
    }
}
