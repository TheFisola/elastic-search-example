package com.thefisola.elasticsearchexample.controller;

import com.thefisola.elasticsearchexample.dto.ProductDto;
import com.thefisola.elasticsearchexample.model.Product;
import com.thefisola.elasticsearchexample.model.ProductSearch;
import com.thefisola.elasticsearchexample.service.ProductElasticSearchService;
import com.thefisola.elasticsearchexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    private final ProductElasticSearchService productElasticSearchService;

    @Autowired
    public ProductController(ProductService productService, ProductElasticSearchService productElasticSearchService) {
        this.productService = productService;
        this.productElasticSearchService = productElasticSearchService;
    }

    @GetMapping("/auto-complete")
    public List<ProductSearch> searchProductAutoComplete(@RequestParam String query) {
        return productElasticSearchService.searchProducts(query);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
