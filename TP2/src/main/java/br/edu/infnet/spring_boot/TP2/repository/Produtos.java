package br.edu.infnet.spring_boot.TP2.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.spring_boot.TP2.model.Produto;

public class Produtos {    
    private List<Produto> produtos;

    public Produtos() {
        if(produtos == null) produtos = new ArrayList<>();
    }

    public List<Produto> getAll(){
        return produtos;
    }

    public Produto getById(Long id){
        for(Produto p: produtos)
            if(id == p.getId()) return p;
        return null;
    }

    public void newProduto(Produto produto){
        produtos.add(produto);
    }

    public void editProduto(Long id, Produto produto){
        int i = 0;
        for(Produto p: produtos){
            if(id == p.getId()) produtos.set(i, produto);
            i++;
        }
    }

    public void deleteById(Long id){
        int i = 0;
        for(Produto p: produtos){
            if(id == p.getId()){
                produtos.remove(i);
                break;
            }
            i++;
        }
    }
}