package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return departamentoService.getDepartamentos();
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<?> getDepartamentoById(@PathVariable Long id){
        return departamentoService.getDepartamentoById(id);
    }    

    @PostMapping("/departamento")
    public ResponseEntity<?> createCursoByAlunoId(@RequestBody Departamento departamento) {
        return departamentoService.createDepartamento(departamento);
    }
    
    @PutMapping("departamento/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @RequestBody Departamento departamento){
        return departamentoService.updateDepartamento(id, departamento);
    }

    @DeleteMapping("/departamento/{id}")
    public ResponseEntity<?> deleteCursoById(@PathVariable Long id){
        return departamentoService.deleteDepartamentoById(id);
    }    
}