package br.edu.infnet.spring_boot.AT.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String senha;
    private String papel;

    public Usuario() {
    }

    public Usuario(String id, String nome, String senha, String papel) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return this.papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
