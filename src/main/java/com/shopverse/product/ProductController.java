package com.shopverse.product;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Product> all(@RequestParam(required=false) String q, @RequestParam(required=false) String category) {
        if (q != null && !q.isBlank()) return repo.findByTitleContainingIgnoreCase(q.trim());
        if (category != null && !category.isBlank() && !category.equalsIgnoreCase("All")) return repo.findByCategoryIgnoreCase(category.trim());
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Product not found"));
    }
}
