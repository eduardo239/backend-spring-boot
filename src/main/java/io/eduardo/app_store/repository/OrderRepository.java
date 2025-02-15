package io.eduardo.app_store.repository;

import io.eduardo.app_store.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


  /**
   * Finds all orders placed by a user with the given ID.
   *
   * @param userId the ID of the user
   * @return a list of orders
   */
  List<Order> findByUserId(Long userId);
}
