package com.drone.drone.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProductRepo extends JpaRepository <Product, Long> {
    Optional<Product> findByName(String name);
}
