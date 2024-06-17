package br.edu.infnet.spring_boot.AT.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.infnet.spring_boot.AT.model.Usuario;
import br.edu.infnet.spring_boot.AT.repository.UsuarioRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByNome(username);
        if(usuario == null) throw new UsernameNotFoundException("Usuário não cadastrado!");
        return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(), Collections.singletonList(new SimpleGrantedAuthority(usuario.getPapel())));
    }
}
