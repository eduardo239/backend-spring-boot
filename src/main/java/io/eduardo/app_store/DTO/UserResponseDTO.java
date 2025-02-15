package io.eduardo.app_store.DTO;

import io.eduardo.app_store.models.Order;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDTO {
  private String id;
  private String username;
  private String name;
  private String email;
  private String role;
  private Date createdAt;
  private List<Order> orders;
}


