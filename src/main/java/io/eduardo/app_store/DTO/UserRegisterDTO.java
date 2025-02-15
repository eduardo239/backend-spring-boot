package io.eduardo.app_store.DTO;


import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRegisterDTO {

  private String name;
  private String username;
  private String email;
  @Size(min = 3, message = "Min 3 characters")
  private String password;


}
