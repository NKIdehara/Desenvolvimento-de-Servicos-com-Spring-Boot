package br.edu.infnet.spring_boot.TP3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.TP3.model.Curso;
import br.edu.infnet.spring_boot.TP3.repository.AlunoRepository;
import br.edu.infnet.spring_boot.TP3.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "cursos")
    public ResponseEntity<?> getCursos(){
        if(cursoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cursos!");
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @Cacheable(value = "cursos", key = "#id")
    public ResponseEntity<?> getCursoById(Long id){
        if(cursoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cursos!");
        if(cursoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não cadastrado!");
        return ResponseEntity.ok(cursoRepository.findById(id));
    }

    public ResponseEntity<?> getCursosByAlunoId(Long idAluno){
        if(alunoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        if(alunoRepository.findById(idAluno).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        return ResponseEntity.ok(cursoRepository.findByAlunoId(idAluno));
    }

    public ResponseEntity<?> createCurso(Curso curso) {
        cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso cadastrado!");
    }
    public ResponseEntity<?> createCursoByAlunoId(Long idAluno, Curso curso){
        if(alunoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        if(alunoRepository.findById(idAluno).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        alunoRepository.findById(idAluno).map(aluno -> {
            curso.setAluno(aluno);
            return cursoRepository.save(curso);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso cadastrado!");
    }

    @CacheEvict(value = "cursos", key = "#id")
    public ResponseEntity<?> updateCurso(Long id, Curso curso){
        cursoRepository.findById(id).map(update -> {
            update.setId(id);
            update.setNome(curso.getNome());
            cursoRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Curso atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não cadastrado!");
        });         
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Curso atualizado!");
    }

    @CacheEvict(value = "cursos", key = "#id")
    public ResponseEntity<?> deleteCursoById(Long id){
        if(cursoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não cadastrado!");
        cursoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Curso excluído!");
    }
}
