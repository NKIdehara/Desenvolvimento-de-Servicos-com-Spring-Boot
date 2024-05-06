package br.edu.infnet.spring_boot.TP1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Math;

import br.edu.infnet.spring_boot.TP1.model.Valores;

@RestController
public class ExponenciacaoController {
    // http://localhost:8080/exponenciacao?valor1=1&valor2=2
    @RequestMapping(method = RequestMethod.GET, value = "/exponenciacao")
    String getExponenciacao(@RequestParam Map<String, String> valor){
        double resultado = Math.pow(Float.parseFloat(valor.get("valor1")), Float.parseFloat(valor.get("valor2")));
        return "O valor da exponenciação é: " + resultado;
    }

    // http://localhost:8080/exponenciacao
    // {
    //     "valor1": "2.0",
    //     "valor2": "2.5"
    // }
    @RequestMapping(method = RequestMethod.POST, value = "/exponenciacao")
    String postExponenciacao(@RequestBody Valores valores){
        return "O valor da exponenciação é: " + Math.pow(valores.getValor1(), valores.getValor2());
    }
}
