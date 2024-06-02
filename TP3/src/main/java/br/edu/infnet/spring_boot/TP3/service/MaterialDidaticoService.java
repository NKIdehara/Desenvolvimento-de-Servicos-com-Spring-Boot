package br.edu.infnet.spring_boot.TP3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.TP3.model.MaterialDidatico;
import br.edu.infnet.spring_boot.TP3.repository.MaterialDidaticoRepository;

@Service
public class MaterialDidaticoService {
    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;

    public ResponseEntity<?> getMaterialDidatico(){
        if(materialDidaticoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe Material Didático!");
        return ResponseEntity.ok(materialDidaticoRepository.findAll());
    }

    public ResponseEntity<?> getMaterialDidaticoById(String id){
        if(materialDidaticoRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe Material Didático!");
        if(materialDidaticoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material Didático não encontrado!");
        return ResponseEntity.ok(materialDidaticoRepository.findById(id));
    }

    public ResponseEntity<?> createMaterialDidatico(MaterialDidatico materialDidatico){
        materialDidaticoRepository.save(materialDidatico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Material Didático cadastrado!");
    }

    public ResponseEntity<?> updateMaterialDidatico(String id, MaterialDidatico materialDidatico){
        materialDidaticoRepository.findById(id).map(update -> {
            update.setId(materialDidatico.getId());
            update.setNome(materialDidatico.getNome());
            update.setMaterial(materialDidatico.getMaterial());
            materialDidaticoRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Material Didático atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material Didático não cadastrado!");
        });
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Material Didático atualizado!");
    }

    public ResponseEntity<?> deleteMaterialDidatico(String id){
        if(materialDidaticoRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material Didático não encontrado!");
        materialDidaticoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Material Didático excluído!");
    }
}