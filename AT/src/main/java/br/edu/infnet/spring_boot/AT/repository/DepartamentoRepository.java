package br.edu.infnet.spring_boot.AT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.spring_boot.AT.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}