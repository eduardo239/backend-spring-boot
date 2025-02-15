package io.eduardo.app_store.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginResponseDTO {

  private String username;
  private String password;
  private String email;
  private String id;
  private String createdAt;
  private String role;

}
