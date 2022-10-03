package com.cenachi.vendasgrasp.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenachi.vendasgrasp.model.Estoque;
import com.cenachi.vendasgrasp.services.EstoqueService;

import lombok.Data;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping()
    public Collection<Estoque> getEstoque() {
        return estoqueService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Estoque> addEstoque(@RequestBody ProdutoNoEstoque form) {
        return ResponseEntity.ok().body(estoqueService.addEstoque(form.getProdutoId(), form.getQuant()));
    }

    @PostMapping("/remove")
    public ResponseEntity<Estoque> removeEstoque(@RequestBody ProdutoNoEstoque form) {
        return ResponseEntity.ok().body(estoqueService.removeEstoque(form.getProdutoId(), form.getQuant()));
    }

}

@Data
class ProdutoNoEstoque {
    private Long produtoId;
    private int quant;
}
