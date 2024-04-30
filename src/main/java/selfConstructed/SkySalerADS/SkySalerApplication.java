package selfConstructed.SkySalerADS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SkySalerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SkySalerApplication.class, args);
  }
}
