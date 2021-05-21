package com.thefisola.elasticsearchexample.service;

import com.thefisola.elasticsearchexample.model.ProductSearch;
import com.thefisola.elasticsearchexample.repository.ProductElasticSearchRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
public class ProductElasticSearchService {
    private final ProductElasticSearchRepository productElasticSearchRepository;
    private final ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    public ProductElasticSearchService(ProductElasticSearchRepository productElasticSearchRepository, ElasticsearchRestTemplate elasticsearchTemplate) {
        this.productElasticSearchRepository = productElasticSearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public void indexSearchableProduct(ProductSearch productSearch) {
        productElasticSearchRepository.save(productSearch);
    }

    public List<ProductSearch> searchProducts(String query) {

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(query, "searchable")
                        .operator(Operator.AND)
                        .fuzziness(Fuzziness.AUTO)
                )
                .build();

        final SearchHits<ProductSearch> products = elasticsearchTemplate.search(searchQuery, ProductSearch.class, IndexCoordinates.of("storefront"));
        return products.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
