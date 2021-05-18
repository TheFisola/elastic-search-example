package com.thefisola.elasticsearchexample.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    String id;
    @Column
    String name;
    @Column
    String description;
    @Column
    int quantity;
    @Column
    long price;
}
