package io.eduardo.app_store.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
//  @JsonManagedReference
  private List<Product> products = new ArrayList<>();


  @ManyToOne
  @JoinColumn(name = "user_id")
//  @JsonBackReference
  @JsonIgnore
  private User user;

  private Date createdAt = new Date();


  /**
   * Adds a product to this order.
   *
   * @param product the product to be added
   */
  public void addProduct(Product product) {
    products.add(product);
    product.setOrder(this);
  }

  /**
   * Removes a product from this order.
   *
   * @param product the product to be removed
   */
  public void removeProduct(Product product) {
    products.remove(product);
    product.setOrder(null);
  }

}

