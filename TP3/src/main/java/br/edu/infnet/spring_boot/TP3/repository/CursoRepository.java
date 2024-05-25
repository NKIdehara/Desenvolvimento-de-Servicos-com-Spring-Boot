package br.edu.infnet.spring_boot.TP3.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.spring_boot.TP3.model.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {   
}