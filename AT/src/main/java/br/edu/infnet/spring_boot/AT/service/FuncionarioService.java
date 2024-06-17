package br.edu.infnet.spring_boot.AT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import br.edu.infnet.spring_boot.AT.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id){
        return funcionarioRepository.findById(id);

    }
    public List<Funcionario> getFuncionariosByDepartamentoId(Long idDepartamento){
        return funcionarioRepository.findByDepartamentoId(idDepartamento);
    }

    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario){
        return funcionarioRepository.findById(id).map(update -> {
            update.setNome(funcionario.getNome());
            update.setEndereco(funcionario.getEndereco());
            update.setTelefone(funcionario.getTelefone());
            update.setEmail(funcionario.getEmail());
            update.setDataNascimento(funcionario.getDataNascimento());
            update.setDepartamento(funcionario.getDepartamento());
            return funcionarioRepository.save(update);
        }).orElseGet(() -> {
            return null;
        });         
    }

    public void deleteFuncionarioById(Long id){
        funcionarioRepository.deleteById(id);
    }
}