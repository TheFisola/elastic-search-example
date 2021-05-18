package com.thefisola.elasticsearchexample.service;

import com.thefisola.elasticsearchexample.model.Product;
import com.thefisola.elasticsearchexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> searchProduct(String query){
        return productRepository.findByName(query);
    }

}
