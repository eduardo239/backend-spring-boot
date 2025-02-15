package io.eduardo.app_store.controller;

import io.eduardo.app_store.models.Order;
import io.eduardo.app_store.models.Product;
import io.eduardo.app_store.models.User;
import io.eduardo.app_store.repository.OrderRepository;
import io.eduardo.app_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserRepository userRepository;

  /**
   * Retrieves a list of all orders.
   *
   * @return a list of orders
   */
  @GetMapping
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  /**
   * Finds an order by ID.
   *
   * @param id the ID of the order
   * @return the order with the given ID, or null if not found
   */
  @GetMapping("/{id}")
  public Order getOrderById(@PathVariable Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  @GetMapping("/by-user-id/{userId}")
  public List<Order> getOrdersByUserId(@PathVariable Long userId) {
    return orderRepository.findByUserId(userId);
  }


  /**
   * Creates a new order and adds it to a user.
   *
   * @param userId the ID of the user
   * @param order  the order to be created
   * @throws IllegalArgumentException if the user with the given ID does not exist
   */
  @PostMapping("/add-order-to-user/{userId}")
  public void addOrderToUser(@PathVariable Long userId, @RequestBody Order order) {

    Optional<User> _user = userRepository.findById(userId);
    System.out.println("[OrderController]" + _user);
    if (_user.isEmpty()) {
      throw new IllegalArgumentException("[OrderController] User not found");
    }

    User user = _user.get();

    user.addOrder(order);
    userRepository.save(user);
  }

  /**
   * Adds a product to an existing order.
   *
   * @param orderId the ID of the order to which the product will be added
   * @param product the product to be added to the order
   * @throws IllegalArgumentException if the order with the given ID does not exist
   */
  @PostMapping("/add-product-to-order/{orderId}")
  public void addProductToOrder(@PathVariable Long orderId, @RequestBody Product product) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));

    order.addProduct(product);
    orderRepository.save(order);
  }


  /**
   * Deletes all orders.
   */
  @DeleteMapping
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }

  /**
   * Deletes an order by ID.
   *
   * @param id the ID of the order
   */
  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    orderRepository.deleteById(id);
  }


}
