package io.eduardo.app_store.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @NotNull
  @Column(unique = true)
  private String username;

  private String name;

  @NotNull
  @Column(unique = true)
  @Email
  private String email;

  @NotNull
  private String password;

  @Column(nullable = false)
  private String role;

  private Date createdAt;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
//  @JsonManagedReference
  private List<Order> orders = new ArrayList<>();


  /**
   * Adds an order to the user.
   *
   * @param order the order to be added
   */
  public void addOrder(Order order) {
    orders.add(order);
    order.setUser(this);
  }

  /**
   * Removes an order from the user's list of orders.
   *
   * @param order the order to be removed
   */
  public void removeOrder(Order order) {
    orders.remove(order);
    order.setUser(null);
  }


  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", orders=" + orders +
            '}';
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
