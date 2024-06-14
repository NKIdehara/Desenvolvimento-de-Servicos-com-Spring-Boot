package br.edu.infnet.spring_boot.AT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Usuario;
import br.edu.infnet.spring_boot.AT.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> getUsuarios(){
        if(usuarioRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem usuários!");
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    public ResponseEntity<?> getUsuarioById(String id){
        if(usuarioRepository.count() == 0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe usuário!");
        if(usuarioRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        return ResponseEntity.ok(usuarioRepository.findById(id));
    }

    public ResponseEntity<?> createUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado!");
    }

    public ResponseEntity<?> updateUsuario(String id, Usuario usuario){
        usuarioRepository.findById(id).map(update -> {
            update.setNome(usuario.getNome());
            update.setSenha(usuario.getSenha());
            update.setPapel(usuario.getPapel());
            usuarioRepository.save(update);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário atualizado!");
        }).orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não cadastrado!");
        });
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário atualizado!");
    }

    public ResponseEntity<?> deleteUsuario(String id){
        if(usuarioRepository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário excluído!");
    }
}