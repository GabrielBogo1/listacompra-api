package com.listadecompras.listacompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.listadecompras.listacompras.entity.product;
import com.listadecompras.listacompras.repository.productRepository;
import com.listadecompras.listacompras.service.productService;

@Controller
@RequestMapping(value = "api/product")
@CrossOrigin
public class productController {
    
    @Autowired
    productRepository productRepository;

    @Autowired
    productService productService;


    @GetMapping("/{id}")
    public ResponseEntity<product> findByIDPath(@PathVariable("id") final Long id) {
        final product product = this.productRepository.findById(id).orElse(null);
        return ResponseEntity.ok(product);
    }

      @GetMapping
    public ResponseEntity<List <product>> listAll() {
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    @PostMapping
    public product createProduct (@RequestBody final product product) {
        try {
            return this.productService.save(product);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") final Long id, @RequestBody final product product) {
        try {
            productService.updateProduct(id,product);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<> (null, HttpStatus.BAD_REQUEST);
        }
    }

}
