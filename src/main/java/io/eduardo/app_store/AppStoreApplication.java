package io.eduardo.app_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(AppStoreApplication.class, args);
    System.out.println("!Started!");
  }

}
