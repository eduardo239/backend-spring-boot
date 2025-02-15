package io.eduardo.app_store.repository;

import io.eduardo.app_store.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


  /**
   * Finds a user by their username.
   *
   * @param username the username to search for
   * @return an optional containing the user if found, or an empty optional
   * otherwise
   */
  Optional<User> findByUsername(@Param("username") String username);

  /**
   * Finds a user by their email address.
   *
   * @param email the email address
   * @return the user, if found
   */
  Optional<User> findByEmail(String email);
}
