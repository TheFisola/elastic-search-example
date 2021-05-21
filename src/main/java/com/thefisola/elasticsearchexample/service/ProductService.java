package com.thefisola.elasticsearchexample.service;

import com.thefisola.elasticsearchexample.dto.ProductDto;
import com.thefisola.elasticsearchexample.event.IndexProductForSearchEvent;
import com.thefisola.elasticsearchexample.model.Product;
import com.thefisola.elasticsearchexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ProductService(ProductRepository productRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity()).build();
        product = productRepository.save(product);
        applicationEventPublisher.publishEvent(new IndexProductForSearchEvent(product));
        return product;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProduct(String query) {
        return productRepository.findByName(query);
    }

}
