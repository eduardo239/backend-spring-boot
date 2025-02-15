package io.eduardo.app_store.controller;


import io.eduardo.app_store.DTO.UserResponseDTO;
import io.eduardo.app_store.models.User;
import io.eduardo.app_store.repository.UserRepository;
import lombok.AllArgsConstructor;
import io.eduardo.app_store.config.JwtUtil;
import io.eduardo.app_store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;
  private final AuthService authService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
    String response = authService.register(
            request.get("username"),
            request.get("password"),
            request.get("email")
    );

    Optional<User> userOptional = userRepository.findByUsername(request.get("username"));
    UserResponseDTO userResponseDTO = new UserResponseDTO();

    if (userOptional.isPresent()) {
      User user = userOptional.get();
      userResponseDTO.setId(user.getId().toString());
      userResponseDTO.setUsername(user.getUsername());
      userResponseDTO.setName(user.getName());
      userResponseDTO.setEmail(user.getEmail());
      userResponseDTO.setRole(user.getRole());
      userResponseDTO.setCreatedAt(user.getCreatedAt());
      userResponseDTO.setOrders(user.getOrders());


      return ResponseEntity.ok(Map.of("user", userResponseDTO, "message", response));
    }

    return ResponseEntity.ok(Map.of("user", request, "message", response));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    if (authService.authenticate(request.get("username"), request.get("password"))) {
      String token = jwtUtil.generateToken(request.get("username"));


      Optional<User> userOptional = userRepository.findByUsername(request.get("username"));
      UserResponseDTO userResponseDTO = new UserResponseDTO();

      if (userOptional.isPresent()) {
        User user = userOptional.get();
        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setOrders(user.getOrders());
        return ResponseEntity.ok(Map.of("user", userResponseDTO,
                "token", token, "message", "Login successful"));
      }

      return ResponseEntity.ok(Map.of("user", userResponseDTO, "token", token, "message", "Login successful"));
    }
    return ResponseEntity.status(401).body("Invalid credentials");
  }
}

