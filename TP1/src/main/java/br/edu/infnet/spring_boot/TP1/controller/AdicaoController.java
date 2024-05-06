package br.edu.infnet.spring_boot.TP1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP1.model.Valores;

@RestController
public class AdicaoController {
    // http://localhost:8080/adicao?valor1=1&valor2=2
    @RequestMapping(method = RequestMethod.GET, value = "/adicao")
    String getAdicao(@RequestParam Map<String, String> valor){
        Float resultado = Float.parseFloat(valor.get("valor1")) + Float.parseFloat(valor.get("valor2"));
        return "O valor da adição é: " + resultado;
    }

    // http://localhost:8080/adicao
    // {
    //     "valor1": "2.0",
    //     "valor2": "2.5"
    // }
    @RequestMapping(method = RequestMethod.POST, value = "/adicao")
    String postAdicao(@RequestBody Valores valores){
        return "O valor da adição é: " + (valores.getValor1() + valores.getValor2());
    }
}
