package br.edu.infnet.spring_boot.AT.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.infnet.spring_boot.AT.model.Usuario;
import br.edu.infnet.spring_boot.AT.repository.UsuarioRepository;
import br.edu.infnet.spring_boot.AT.service.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        if(usuarioRepository.findUsuarioByNome("teste_admin") == null) {
            Usuario usuario = new Usuario();
            usuario.setNome("teste_admin");
            usuario.setSenha(passwordEncoder().encode("12345678"));
            usuario.setPapel("ADMIN");
            usuarioRepository.save(usuario);
        }
        return new CustomerUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService (userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }    

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authenticationProvider())
            .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests (auth -> auth
                .requestMatchers("/api/public/**"). permitAll() 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.permitAll())
            .logout(logout -> logout.permitAll());
        return http.build();
    }
}
