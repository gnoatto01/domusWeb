package com.soluctions.attos.domus.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.soluctions.attos.domus.entities.Role.Roles;
import com.soluctions.attos.domus.entities.User;
import com.soluctions.attos.domus.repositories.RoleRepository;
import com.soluctions.attos.domus.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AdminUser implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository
                .findByRoleName(Roles.ADMIN.name());

        if (roleAdmin == null) {
            throw new RuntimeException("Role ADMIN not found in the database.");
        }

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin exists");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("@ttosAdmin!@#"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                });
    }
}
