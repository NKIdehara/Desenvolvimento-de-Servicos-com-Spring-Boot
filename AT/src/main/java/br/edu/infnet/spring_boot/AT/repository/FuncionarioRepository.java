package br.edu.infnet.spring_boot.AT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.spring_boot.AT.model.Funcionario;
import java.util.List;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByDepartamentoId(Long idDepartamento);
}