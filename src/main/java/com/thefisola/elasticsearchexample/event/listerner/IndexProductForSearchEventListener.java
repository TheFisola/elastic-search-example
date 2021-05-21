package com.thefisola.elasticsearchexample.event.listerner;

import com.thefisola.elasticsearchexample.event.IndexProductForSearchEvent;
import com.thefisola.elasticsearchexample.model.ProductSearch;
import com.thefisola.elasticsearchexample.service.ProductElasticSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class IndexProductForSearchEventListener {

    private final ProductElasticSearchService elasticSearchService;

    @Autowired
    public IndexProductForSearchEventListener(ProductElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @Async
    @EventListener
    public void handleProductIndexing(IndexProductForSearchEvent indexProductForSearchEvent) {
        var createdProduct = indexProductForSearchEvent.getProduct();
        var productSearch = new ProductSearch();
        BeanUtils.copyProperties(createdProduct, productSearch);
        productSearch.buildSearchable();
        elasticSearchService.indexSearchableProduct(productSearch);
    }
}
