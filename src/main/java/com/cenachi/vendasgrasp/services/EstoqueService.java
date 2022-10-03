package com.cenachi.vendasgrasp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.cenachi.vendasgrasp.model.Estoque;
import com.cenachi.vendasgrasp.model.Produto;
import com.cenachi.vendasgrasp.repositories.EstoqueRepo;
import com.cenachi.vendasgrasp.repositories.ProdutoRepo;

@Service
public class EstoqueService {
    private final ProdutoRepo produtoRepo;
    private final EstoqueRepo estoqueRepo;

    @Autowired
    public EstoqueService(ProdutoRepo produtoRepo, EstoqueRepo estoqueRepo) {
        this.produtoRepo = produtoRepo;
        this.estoqueRepo = estoqueRepo;
    }

    @Transactional
    public List<Estoque> getAll() {
        return estoqueRepo.findAll();
    }

    @Transactional
    public Estoque addEstoque(Long produtoId, int quant) {
        Optional<Produto> produto = produtoRepo.findById(produtoId);
        Optional<Estoque> estoque = estoqueRepo.findById(produtoId);

        if (produto.isPresent()) {
            Produto produtoExistente = produto.get();
            if (estoque.isPresent()) {
                Estoque estoqueExistente = estoque.get();

                estoqueExistente.setQuant(estoqueExistente.getQuant() + quant);

                return estoqueRepo.save(estoqueExistente);
            } else {
                return estoqueRepo.save(new Estoque(produtoExistente.getId(), produtoExistente.getNome(),
                        produtoExistente.getPreco(), quant));
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não possui este produto!");
        }
    }

    @Transactional
    public Estoque removeEstoque(Long produtoId, int quant) {
        Optional<Estoque> estoque = estoqueRepo.findById(produtoId);

        if (estoque.isPresent()) {
            Estoque estoqueExistente = estoque.get();

            if (estoqueExistente.getQuant() > quant) {
                estoqueExistente.setQuant(estoqueExistente.getQuant() - quant);
                return estoqueRepo.save(estoqueExistente);

            } else if (estoqueExistente.getQuant() < quant) {
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Estoque insuficiente para compra!");

            } else {
                estoqueRepo.deleteById(produtoId);
                throw new ResponseStatusException(HttpStatus.OK, "Estoque todo vendido");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado no estoque!");
        }
    }
}