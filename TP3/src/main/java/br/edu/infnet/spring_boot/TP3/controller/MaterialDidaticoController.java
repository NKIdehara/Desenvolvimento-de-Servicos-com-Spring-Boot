package br.edu.infnet.spring_boot.TP3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP3.model.MaterialDidatico;
import br.edu.infnet.spring_boot.TP3.service.MaterialDidaticoService;

@RestController
public class MaterialDidaticoController {
    @Autowired
    private MaterialDidaticoService materialDidaticoService;
    
    @GetMapping("/material")
    public ResponseEntity<?> getMaterial(){
        return materialDidaticoService.getMaterialDidatico();
    }

    @GetMapping("/material/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable String id){
        return materialDidaticoService.getMaterialDidaticoById(id);
    }    

    @PostMapping("/material")
    public ResponseEntity<?> createMaterial(@RequestBody MaterialDidatico materialDidatico) {
        return materialDidaticoService.createMaterialDidatico(materialDidatico);
    }

    @PutMapping("material/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable String id, @RequestBody MaterialDidatico materialDidatico){
        return materialDidaticoService.updateMaterialDidatico(id, materialDidatico);
    }

    @DeleteMapping("/material/{id}")
    public ResponseEntity<?> deleteMaterialById(@PathVariable String id){
        return materialDidaticoService.deleteMaterialDidatico(id);
    }
}