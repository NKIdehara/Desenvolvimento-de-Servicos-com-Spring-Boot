package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.AT.model.Usuario;
import br.edu.infnet.spring_boot.AT.service.UsuarioService;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/usuario")
    public ResponseEntity<?> getUsuarios(){
        return usuarioService.getUsuarios();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id){
        return usuarioService.getUsuarioById(id);
    }    

    @PostMapping("/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String id){
        return usuarioService.deleteUsuario(id);
    }
}