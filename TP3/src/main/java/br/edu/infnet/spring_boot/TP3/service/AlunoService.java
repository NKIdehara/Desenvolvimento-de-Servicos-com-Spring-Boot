package br.edu.infnet.spring_boot.TP3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.TP3.model.Aluno;
import br.edu.infnet.spring_boot.TP3.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Cacheable(value = "alunos")
    public ResponseEntity<?> getAlunos(){
        if(alunoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @Cacheable(value = "alunos", key = "#id")
    public ResponseEntity<?> getAlunoById(Long id){
        if(alunoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem alunos!");
        if(alunoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        return ResponseEntity.ok(alunoRepository.findById(id));
    }    

    public ResponseEntity<?> createAluno(Aluno aluno) {
        alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno cadastrado!");
    }

    @CacheEvict(value = "alunos", key = "#id")
    public ResponseEntity<?> updateAluno(Long id, Aluno aluno){
        alunoRepository.findById(id).map(update -> {
            update.setId(id);
            update.setNome(aluno.getNome());
            update.setCursos(aluno.getCursos());
            alunoRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Aluno atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        });         
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Aluno atualizado!");
    }

    @CacheEvict(value = "alunos", key = "#id")
    public ResponseEntity<?> deleteAlunoById(Long id){
        if(alunoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não cadastrado!");
        alunoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Aluno excluído!");
    }
}
