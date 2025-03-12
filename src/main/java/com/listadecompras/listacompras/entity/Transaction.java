package com.listadecompras.listacompras.entity;
import com.listadecompras.listacompras.enums.Intent;
import com.listadecompras.listacompras.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table
public class Transaction {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "idPaypal")
    private Long idPaypal;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "intent", nullable = false)
    private Intent intent;
}
