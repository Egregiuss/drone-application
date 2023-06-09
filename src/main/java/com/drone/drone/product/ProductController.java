package com.drone.drone.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepo productRepo;

    public ProductController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable Long id) {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                return ResponseEntity.ok(product);
            }else {
                return ResponseEntity.notFound().build();
            }


    }

    @PostMapping("/add-product")
    public ResponseEntity <Product> addProduct(@RequestBody Product product){
        Product newProduct = productRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()){
            productRepo.delete(optionalProduct.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }
}
