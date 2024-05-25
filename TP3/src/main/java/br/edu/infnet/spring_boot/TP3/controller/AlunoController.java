package br.edu.infnet.spring_boot.TP3.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP3.model.Aluno;
import br.edu.infnet.spring_boot.TP3.repository.AlunoRepository;

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
public class AlunoController {
    @Autowired
    private AlunoRepository alunos;
    
    @GetMapping("/aluno")
    public ResponseEntity<?> getAlunos(){
        if(alunos.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        return ResponseEntity.ok(alunos.findAll());
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id){
        if(alunos.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        if(alunos.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        return ResponseEntity.ok(alunos.findById(id));
    }    

    @PostMapping("/aluno")
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno) {
        alunos.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno cadastrado!");
    }

    @PutMapping("aluno/{id}")
    public void updateAluno(@PathVariable Long id, @RequestBody Aluno aluno){
        // alunos.
    }

    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<?> deleteAlunoById(@PathVariable Long id){
        if(alunos.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");

        alunos.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Aluno excluído!");
    }
}
