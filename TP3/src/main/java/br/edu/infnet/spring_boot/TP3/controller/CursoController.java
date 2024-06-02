package br.edu.infnet.spring_boot.TP3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP3.model.Curso;
import br.edu.infnet.spring_boot.TP3.service.CursoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping("/curso")
    public ResponseEntity<?> getCursos(){
        return cursoService.getCursos();
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id){
        return cursoService.getCursoById(id);
    }    

    @GetMapping("/curso/aluno/{idAluno}")
    public ResponseEntity<?> getCursosByAlunoId(@PathVariable Long idAluno) {        
        return cursoService.getCursosByAlunoId(idAluno);
    }

    @PostMapping("/curso/aluno/{idAluno}")
    public ResponseEntity<?> createCursoByAlunoId(@PathVariable Long idAluno, @RequestBody Curso curso) {        
        return cursoService.createCursoByAlunoId(idAluno, curso);
    }
    
    @PutMapping("curso/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @RequestBody Curso curso){
        return cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> deleteCursoById(@PathVariable Long id){
        return cursoService.deleteCursoById(id);
    }    
}
