package br.edu.infnet.spring_boot.TP3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.spring_boot.TP3.model.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}