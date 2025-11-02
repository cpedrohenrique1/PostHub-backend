package edu.posthub.posthub.configs;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.posthub.posthub.entities.Role;
import edu.posthub.posthub.entities.User;
import edu.posthub.posthub.repositories.RoleRepository;
import edu.posthub.posthub.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        Optional<Role> roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        Optional<User> userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
            (user) -> {
                System.out.println("Admin user already exists");
            }, 
            () -> {
            roleAdmin.ifPresent((role) -> {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRoles(Set.of(role));
                userRepository.save(user);
            });
        });
    }
}
