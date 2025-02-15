package io.eduardo.app_store.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginDTO {

  @NotNull
  private String username;
  @NotNull
  private String password;

}
