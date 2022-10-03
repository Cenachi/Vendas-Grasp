package com.cenachi.vendasgrasp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenachi.vendasgrasp.model.Produto;
import com.cenachi.vendasgrasp.repositories.ProdutoRepo;

@Service
public class ProdutoService {
    private final ProdutoRepo produtoRepo;

    @Autowired
    public ProdutoService(ProdutoRepo produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    @Transactional
    public List<Produto> getAll() {
        return produtoRepo.findAll();
    }

    @Transactional
    public Produto addProduto(Produto produto) {
        return produtoRepo.save(produto);
    }
}
