package com.cenachi.vendasgrasp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenachi.vendasgrasp.model.Produto;

public interface ProdutoRepo extends JpaRepository<Produto,Long> {
    
}
