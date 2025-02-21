package io.eduardo.app_store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "t_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private Double price;

  private double quantity = 1.0;

  private Date createdAt = new Date();


  @ManyToOne
  @JoinColumn(name = "order_id")
//  @JsonBackReference
  @JsonIgnore
  private Order order;


}
