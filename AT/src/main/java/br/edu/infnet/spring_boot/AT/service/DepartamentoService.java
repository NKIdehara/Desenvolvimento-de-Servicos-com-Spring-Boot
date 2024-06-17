package br.edu.infnet.spring_boot.AT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Departamento;
import br.edu.infnet.spring_boot.AT.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> getDepartamentos(){
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> getDepartamentoById(Long id){
        return departamentoRepository.findById(id);
    }

    public Departamento createDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(Long id, Departamento departamento){
        return departamentoRepository.findById(id).map(update -> {
            update.setNome(departamento.getNome());
            update.setLocal(departamento.getLocal());
            return departamentoRepository.save(update);
        }).orElseGet(() -> {
            return null;
        });         
    }

    public void deleteDepartamentoById(Long id){
        departamentoRepository.deleteById(id);
    }
}