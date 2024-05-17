package br.edu.infnet.spring_boot.TP2.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.spring_boot.TP2.model.Produto;
import br.edu.infnet.spring_boot.TP2.repository.Produtos;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class ProdutoController {
    private Produtos produtos = new Produtos();
    
    @GetMapping("/produto")
    public List<Produto> getProdutos(){
        return produtos.getAll();        
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoById(@PathVariable Long id){
        return produtos.getById(id);
    }    

    @PostMapping("/produto")
    public void postProduto(@RequestBody Produto produto){
        produtos.newProduto(produto);
    }

    @PutMapping("produto/{id}")
    public void putProduto(@PathVariable Long id, @RequestBody Produto produto){
        produtos.editProduto(id, produto);
    }

    @DeleteMapping("/produto/{id}")
    public void deleteProdutoById(@PathVariable Long id){
        produtos.deleteById(id);
    }
}
