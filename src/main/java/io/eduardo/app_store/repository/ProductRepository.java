package io.eduardo.app_store.repository;

import io.eduardo.app_store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
