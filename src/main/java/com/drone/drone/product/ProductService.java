package com.drone.drone.product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductByName(String productName) {
        return productRepo.findByName(productName)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productName));
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepo.findAllById(productIds);
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new NoSuchElementException("Product with ID " + productId + " not found");
        }

    }

    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setWeight(updatedProduct.getWeight());
            existingProduct.setPrice(updatedProduct.getPrice());

            Product savedProduct = productRepo.save(existingProduct);
            return ResponseEntity.ok(savedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

}
