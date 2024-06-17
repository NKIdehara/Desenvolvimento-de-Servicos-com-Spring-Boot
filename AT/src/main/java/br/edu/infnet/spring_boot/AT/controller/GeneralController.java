package br.edu.infnet.spring_boot.AT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @GetMapping("/")
    public String getRoot() {
        return "Conexão OK!";
    }
    
}
