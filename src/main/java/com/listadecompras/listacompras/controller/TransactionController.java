package com.listadecompras.listacompras.controller;
import com.listadecompras.listacompras.entity.Transaction;
import com.listadecompras.listacompras.repository.TransactionRepository;
import com.listadecompras.listacompras.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "api/transaction")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findByIDPath(@PathVariable("id") final Long id) {
        final Transaction transaction = this.transactionRepository.findById(id).orElse(null);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listAll() {
        return ResponseEntity.ok(this.transactionRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <HttpStatus> createTransaction(@RequestBody final Transaction transaction) {
        try {
            this.transactionService.save(transaction);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") final Long id, @RequestBody final Transaction transaction) {
        try {
            transaction.setId(id);
            transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") final Long id) {
        try {
            this.transactionService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
