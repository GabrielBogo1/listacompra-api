package com.listadecompras.listacompras.repository;

import com.listadecompras.listacompras.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <Payment,Long> {
}
