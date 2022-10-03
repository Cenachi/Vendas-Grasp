package com.cenachi.vendasgrasp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenachi.vendasgrasp.model.Estoque;

public interface EstoqueRepo extends JpaRepository<Estoque,Long>{
    
}
