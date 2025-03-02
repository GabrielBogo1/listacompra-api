package com.listadecompras.listacompras.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table
public class Product {
   
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "nome_produto", nullable = false)
    private String productName;

    @Column(name = "observacao_produto")
    private String productObservation;
}
