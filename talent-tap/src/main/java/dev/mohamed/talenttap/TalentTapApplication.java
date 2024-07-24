package dev.mohamed.talenttap;

import dev.mohamed.talenttap.role.Role;
import dev.mohamed.talenttap.role.RoleName;
import dev.mohamed.talenttap.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TalentTapApplication {

  public static void main(String[] args) {
    SpringApplication.run(TalentTapApplication.class, args);
  }
  @Bean
  public CommandLineRunner runner(RoleRepository roleRepository) {
    return args -> {
      for (RoleName name : RoleName.values()) {
        if (roleRepository.findByName(name).isEmpty()) {
          var role = new Role();
          role.setName(name);
          roleRepository.save(role);
        }
      }
    };
  }

}
