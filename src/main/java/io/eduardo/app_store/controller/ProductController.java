package io.eduardo.app_store.controller;

import io.eduardo.app_store.models.Product;
import io.eduardo.app_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/all/products")
  public Iterable<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @GetMapping
  public Page<Product> getProductsByPage(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable Long id) {
    return productRepository.findById(id).orElse(null);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productRepository.save(product);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productRepository.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllProducts() {
    productRepository.deleteAll();
  }
}
