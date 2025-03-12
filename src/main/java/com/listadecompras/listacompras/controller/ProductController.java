package com.listadecompras.listacompras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.listadecompras.listacompras.entity.Product;
import com.listadecompras.listacompras.repository.ProductRepository;
import com.listadecompras.listacompras.service.ProductService;

@Controller
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findByIDPath(@PathVariable("id") final Long id) {
        final Product product = this.productRepository.findById(id).orElse(null);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <HttpStatus> createProduct(@RequestBody final Product product) {
        try {
            this.productService.save(product);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") final Long id, @RequestBody final Product product) {
        try {
            product.setId(id);
            productService.updateProduct(id, product);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") final Long id) {
        try {
            this.productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
