package br.edu.infnet.spring_boot.TP3.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP3.model.Aluno;
import br.edu.infnet.spring_boot.TP3.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AlunoController {
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping("/aluno")
    public ResponseEntity<?> getAlunos(){
        return alunoService.getAlunos();
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id){
        return alunoService.getAlunoById(id);
    }    

    @PostMapping("/aluno")
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno) {
        return alunoService.createAluno(aluno);
    }

    @PutMapping("aluno/{id}")
    public ResponseEntity<?> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno){
        return alunoService.updateAluno(id, aluno);
    }

    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<?> deleteAlunoById(@PathVariable Long id){
        return alunoService.deleteAlunoById(id);
    }
}
