package com.thefisola.elasticsearchexample.repository;

import com.thefisola.elasticsearchexample.model.ProductSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductElasticSearchRepository extends ElasticsearchRepository<ProductSearch, String> {
     List<ProductSearch> findByNameOrDescription(String name, String description);
}
