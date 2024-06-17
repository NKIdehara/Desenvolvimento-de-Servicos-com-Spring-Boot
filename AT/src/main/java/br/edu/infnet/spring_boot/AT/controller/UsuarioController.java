package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        if(usuarioService.getUsuarios().isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem usuários!");
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id){
        if(usuarioService.getUsuarioById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }    

    @PostMapping("/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        if(usuarioService.createUsuario(usuario) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível cadastrar Usuário!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado!");
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Usuario update = usuarioService.updateUsuario(id, usuario);
        if(update == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário atualizado!");
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String id){
        if(usuarioService.getUsuarioById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário excluído!");
    }
}