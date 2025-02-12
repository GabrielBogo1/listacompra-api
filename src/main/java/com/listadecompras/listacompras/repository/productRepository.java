package com.listadecompras.listacompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listadecompras.listacompras.entity.product;

public interface productRepository extends JpaRepository <product,Long> {
}