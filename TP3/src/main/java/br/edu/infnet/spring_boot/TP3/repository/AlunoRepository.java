package br.edu.infnet.spring_boot.TP3.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.spring_boot.TP3.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}