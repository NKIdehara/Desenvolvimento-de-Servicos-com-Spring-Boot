package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.AT.model.Departamento;
import br.edu.infnet.spring_boot.AT.service.DepartamentoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DepartamentoController {
    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/departamento")
    public ResponseEntity<?> getDepartamentos(){
        if(departamentoService.getDepartamentos().isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem departamentos!");
        return ResponseEntity.ok(departamentoService.getDepartamentos());
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<?> getDepartamentoById(@PathVariable Long id){
        if(departamentoService.getDepartamentoById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não existente!");
        return ResponseEntity.ok(departamentoService.getDepartamentoById(id));
    }    

    @PostMapping("/departamento")
    public ResponseEntity<?> createCursoByAlunoId(@RequestBody Departamento departamento) {
        if(departamentoService.createDepartamento(departamento) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível cadastrar Departamento!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Departamento cadastrado!");        
    }
    
    @PutMapping("departamento/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @RequestBody Departamento departamento){
        Departamento update = departamentoService.updateDepartamento(id, departamento);
        if(update == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não existente!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Departamento atualizado!");
    }

    @DeleteMapping("/departamento/{id}")
    public ResponseEntity<?> deleteCursoById(@PathVariable Long id){
        if(departamentoService.getDepartamentoById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não existente!");
        departamentoService.deleteDepartamentoById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Departamento excluído!");
    }    
}