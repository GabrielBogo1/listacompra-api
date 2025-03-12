package com.listadecompras.listacompras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listadecompras.listacompras.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long> {
}