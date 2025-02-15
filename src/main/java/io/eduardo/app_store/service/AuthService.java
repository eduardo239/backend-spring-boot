package io.eduardo.app_store.service;

import io.eduardo.app_store.models.User;
import io.eduardo.app_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public String register(String username, String password, String email) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already exists!");
    }

    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    user.setRole("USER");
    user.setCreatedAt(new Date());
    userRepository.save(user);
    return "User registered successfully!";
  }

  public boolean authenticate(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username);
    return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
  }
}
