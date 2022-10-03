package com.cenachi.vendasgrasp.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenachi.vendasgrasp.model.Produto;
import com.cenachi.vendasgrasp.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping()
    public Collection<Produto> getProdutos() {
        return produtoService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        Produto produtoAdd = produtoService.addProduto(produto);
        return new ResponseEntity<>(produtoAdd, HttpStatus.CREATED);
    }
}
