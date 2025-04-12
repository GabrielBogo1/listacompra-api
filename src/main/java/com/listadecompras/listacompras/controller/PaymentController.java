package com.listadecompras.listacompras.controller;

import com.listadecompras.listacompras.entity.Payment;
import com.listadecompras.listacompras.repository.PaymentRepository;
import com.listadecompras.listacompras.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/payment")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findByIDPath(@PathVariable("id") final Long id) {
        final Payment payment = this.paymentRepository.findById(id).orElse(null);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> listAll() {
        return ResponseEntity.ok(this.paymentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <HttpStatus> createPayment(@RequestBody final Payment payment) {
        try {
            this.paymentService.save(payment);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") final Long id, @RequestBody final Payment payment) {
        try {
            payment.setId(id);
            paymentService.updatePayment(id, payment);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") final Long id) {
        try {
            this.paymentService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
