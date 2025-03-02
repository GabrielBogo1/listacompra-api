package com.listadecompras.listacompras.entity;

import com.listadecompras.listacompras.payment.Amount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table
public class Payment {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "idPaypal")
    private Long idPaypal;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "amount", nullable = false )
    @Embedded
    private Amount amount;
}
