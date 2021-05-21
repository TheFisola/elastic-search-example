package com.thefisola.elasticsearchexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "storefront")
@Data
@JsonIgnoreProperties({"quantity", "price", "searchable"})
public class ProductSearch {
    @Id
    private String id;
    private String name;
    private String description;
    private int quantity;
    private long price;
    private String searchable;

    public void buildSearchable() {
        this.searchable = this.name + " " + this.description;
    }
}
