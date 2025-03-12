package com.listadecompras.listacompras.service;

import com.listadecompras.listacompras.entity.Payment;
import com.listadecompras.listacompras.entity.Product;
import com.listadecompras.listacompras.repository.PaymentRepository;
import com.listadecompras.listacompras.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void updatePayment (final Long id, final Payment payment) {

        final Payment paymentSaved = this.paymentRepository.findById(id).orElse(null);

        if (paymentSaved == null || !paymentSaved.getId().equals(id)) {
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(payment, paymentSaved);
        this.paymentRepository.save(paymentSaved);
    }

    public void deletePayment (final Long id){
        final Payment dataPayment = this.paymentRepository.findById(id).orElse(null);

        if (dataPayment == null || !dataPayment.getId().equals(id)){
            throw new RuntimeException();
        }

        assert dataPayment != null;
        this.paymentRepository.delete(dataPayment);
    }
}
