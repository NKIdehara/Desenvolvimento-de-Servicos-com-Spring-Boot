package br.edu.infnet.spring_boot.AT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import br.edu.infnet.spring_boot.AT.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ResponseEntity<?> getFuncionarios(){
        if(funcionarioRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem funcionários!");
        return ResponseEntity.ok(funcionarioRepository.findAll());
    }

    public ResponseEntity<?> getFuncionarioById(Long id){
        if(funcionarioRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem funcionários!");
        if(funcionarioRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionário não cadastrado!");
        return ResponseEntity.ok(funcionarioRepository.findById(id));

    }
    public ResponseEntity<?> getFuncionariosByDepartamentoId(Long idDepartamento){
        if(funcionarioRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem funcionários!");
        return ResponseEntity.ok(funcionarioRepository.findByDepartamentoId(idDepartamento));
    }

    public ResponseEntity<?> createFuncionario(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário cadastrado!");
    }

    public ResponseEntity<?> updateFuncionario(Long id, Funcionario funcionario){
        return funcionarioRepository.findById(id).map(update -> {
            update.setNome(funcionario.getNome());
            update.setEndereco(funcionario.getEndereco());
            update.setTelefone(funcionario.getTelefone());
            update.setEmail(funcionario.getEmail());
            update.setDataNascimento(funcionario.getDataNascimento());
            update.setDepartamento(funcionario.getDepartamento());
            funcionarioRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Funcionário atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não cadastrado!");
        });         
    }

    public ResponseEntity<?> deleteFuncionarioById(Long id){
        if(funcionarioRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não cadastrado!");
        funcionarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Funcionário excluído!");
    }
}