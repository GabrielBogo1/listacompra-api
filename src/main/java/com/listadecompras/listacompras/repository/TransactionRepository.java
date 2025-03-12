package com.listadecompras.listacompras.repository;

import com.listadecompras.listacompras.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction,Long> {
}
