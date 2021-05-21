package com.thefisola.elasticsearchexample.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private String name;
    private String description;
    private int quantity;
    private long price;
}
