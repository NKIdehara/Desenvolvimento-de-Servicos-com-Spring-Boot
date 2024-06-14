package br.edu.infnet.spring_boot.AT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Departamento;
import br.edu.infnet.spring_boot.AT.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public ResponseEntity<?> getDepartamentos(){
        if(departamentoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem departamentos!");
        return ResponseEntity.ok(departamentoRepository.findAll());
    }

    public ResponseEntity<?> getDepartamentoById(Long id){
        if(departamentoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem departamentos!");
        if(departamentoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não cadastrado!");
        return ResponseEntity.ok(departamentoRepository.findById(id));
    }

    public ResponseEntity<?> createDepartamento(Departamento departamento) {
        departamentoRepository.save(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Departamento cadastrado!");
    }

    public ResponseEntity<?> updateDepartamento(Long id, Departamento departamento){
        return departamentoRepository.findById(id).map(update -> {
            update.setNome(departamento.getNome());
            update.setLocal(departamento.getLocal());
            departamentoRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Departamento atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não cadastrado!");
        });         
    }

    public ResponseEntity<?> deleteDepartamentoById(Long id){
        if(departamentoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não cadastrado!");
        departamentoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Departamento excluído!");
    }
}