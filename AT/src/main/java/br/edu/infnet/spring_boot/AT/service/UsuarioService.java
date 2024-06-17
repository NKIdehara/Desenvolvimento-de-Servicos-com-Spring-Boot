package br.edu.infnet.spring_boot.AT.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Usuario;
import br.edu.infnet.spring_boot.AT.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(String id){
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(String id, Usuario usuario){
        return usuarioRepository.findById(id).map(update -> {
            update.setId(id);
            update.setNome(usuario.getNome());
            update.setSenha(usuario.getSenha());
            update.setPapel(usuario.getPapel());
            return usuarioRepository.save(update);
        }).orElseGet(() -> {
            return null;
        });
    }

    public void deleteUsuario(String id){
        usuarioRepository.deleteById(id);
    }
}