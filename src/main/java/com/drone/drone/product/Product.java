package com.drone.drone.product;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    private String name;
    private Integer price;
    private Integer weight;
}
