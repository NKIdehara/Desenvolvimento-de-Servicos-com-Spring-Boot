package br.edu.infnet.spring_boot.TP1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP1.model.Valores;

@RestController
public class SubtracaoController {
    // http://localhost:8080/subtracao?valor1=1&valor2=2
    @RequestMapping(method = RequestMethod.GET, value = "/subtracao")
    String getSubtracao(@RequestParam Map<String, String> valor){
        Float resultado = Float.parseFloat(valor.get("valor1")) - Float.parseFloat(valor.get("valor2"));
        return "O valor da subtração é: " + resultado;
    }

    // http://localhost:8080/subtracao
    // {
    //     "valor1": "2.0",
    //     "valor2": "2.5"
    // }
    @RequestMapping(method = RequestMethod.POST, value = "/subtracao")
    String postSubtracao(@RequestBody Valores valores){
        return "O valor da subtração é: " + (valores.getValor1() - valores.getValor2());
    }
}
