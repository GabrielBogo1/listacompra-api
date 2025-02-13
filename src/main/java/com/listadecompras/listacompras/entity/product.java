package com.listadecompras.listacompras.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class product {
   
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "nome_produto", nullable = false)
    private String productName;

    @Column(name = "observacao_produto")
    private String productObservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductObservation() {
        return productObservation;
    }

    public void setProductObservation(String productObservation) {
        this.productObservation = productObservation;
    }
}
