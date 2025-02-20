package com.listadecompras.listacompras.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.listadecompras.listacompras.entity.product;
import com.listadecompras.listacompras.repository.productRepository;

@Service
public class productService {
       
    @Autowired
    productRepository productRepository;

    public product save(product product) {
        return productRepository.save(product);
    }

    public void updateProduct (final Long id, final product product) {

        final product productSaved = this.productRepository.findById(id).orElse(null);

        if (productSaved == null || !productSaved.getId().equals(id)) {
            throw new RuntimeException();
        }

        BeanUtils.copyProperties(product, productSaved);
        this.productRepository.save(productSaved);
    }

    public void deleteProduct (final Long id){
        final product dataProduct = this.productRepository.findById(id).orElse(null);

        if (dataProduct == null || !dataProduct.getId().equals(id)){
            throw new RuntimeException();
        }

        assert dataProduct != null;
        this.productRepository.delete(dataProduct);
    }
}
