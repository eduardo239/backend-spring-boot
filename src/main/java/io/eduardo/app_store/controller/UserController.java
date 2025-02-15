package io.eduardo.app_store.controller;

import io.eduardo.app_store.DTO.UserResponseDTO;
import io.eduardo.app_store.models.User;
import io.eduardo.app_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/details")
  public ResponseEntity<?> getUserDetails() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Optional<User> userDetails = userRepository.findByUsername(username);
    UserResponseDTO userResponseDTO = new UserResponseDTO();


    if (userDetails.isPresent()) {
      Long id = userDetails.get().getId();
      userResponseDTO.setId(id.toString());
      userResponseDTO.setUsername(userDetails.get().getUsername());
      userResponseDTO.setName(userDetails.get().getName());
      userResponseDTO.setEmail(userDetails.get().getEmail());
      userResponseDTO.setRole(userDetails.get().getRole());
      userResponseDTO.setCreatedAt(userDetails.get().getCreatedAt());
      userResponseDTO.setOrders(userDetails.get().getOrders());

      return ResponseEntity.ok(userResponseDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  /**
   * Retrieves a list of all users.
   *
   * @return a list of all users
   */
  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Finds a user by ID.
   *
   * @param id the ID of the user
   * @return the user with the given ID, or null if not found
   */
  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.findById(id).orElse(null);
  }


  /**
   * Patches an existing user.
   *
   * @param user the user to be patched, must contain the ID of the user to be patched
   * @return the patched user, or null if not found
   */
  @PatchMapping
  public User patchUser(@RequestBody User user) {

    User existingUser = userRepository
            .findById(user.getId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (existingUser != null) {
      existingUser.setName(user.getName());
      existingUser.setUsername(user.getUsername());
      return userRepository.save(existingUser);
    }
    return null;
  }

  /**
   * Deletes all users.
   */
  @DeleteMapping
  public void deleteUser() {
    userRepository.deleteAll();
  }

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user
   */
  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}
