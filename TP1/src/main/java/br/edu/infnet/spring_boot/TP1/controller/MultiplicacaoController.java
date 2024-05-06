package br.edu.infnet.spring_boot.TP1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP1.model.Valores;

@RestController
public class MultiplicacaoController {
    // http://localhost:8080/multiplicacao?valor1=1&valor2=2
    @RequestMapping(method = RequestMethod.GET, value = "/multiplicacao")
    String getMultiplicacao(@RequestParam Map<String, String> valor){
        Float resultado = Float.parseFloat(valor.get("valor1")) * Float.parseFloat(valor.get("valor2"));
        return "O valor da multiplicação é: " + resultado;
    }

    // http://localhost:8080/multiplicacao
    // {
    //     "valor1": "2.0",
    //     "valor2": "2.5"
    // }
    @RequestMapping(method = RequestMethod.POST, value = "/multiplicacao")
    String postMultiplicacao(@RequestBody Valores valores){
        return "O valor da multiplicação é: " + (valores.getValor1() * valores.getValor2());
    }
}
