package br.edu.infnet.spring_boot.AT.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.spring_boot.AT.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findUsuarioByNome(String nome);
}
