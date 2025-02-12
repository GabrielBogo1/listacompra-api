package com.listadecompras.listacompras.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
