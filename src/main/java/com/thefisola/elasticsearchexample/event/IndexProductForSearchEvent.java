package com.thefisola.elasticsearchexample.event;

import com.thefisola.elasticsearchexample.model.Product;
import lombok.Getter;

@Getter
public class IndexProductForSearchEvent {
    private final Product product;

    public IndexProductForSearchEvent(Product product) {
        this.product = product;
    }
}
