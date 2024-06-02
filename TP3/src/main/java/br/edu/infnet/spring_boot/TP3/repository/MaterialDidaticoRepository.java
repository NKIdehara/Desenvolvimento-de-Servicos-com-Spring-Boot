package br.edu.infnet.spring_boot.TP3.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.spring_boot.TP3.model.MaterialDidatico;

@Repository
public interface MaterialDidaticoRepository extends MongoRepository<MaterialDidatico, String> {   
}
