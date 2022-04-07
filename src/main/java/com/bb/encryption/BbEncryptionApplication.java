package com.bb.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BbEncryptionApplication {

  public static void main(String[] args) {
    SpringApplication.run(BbEncryptionApplication.class, args);
  }

}
