package com.tracking.backend.config;

import com.tracking.backend.entity.User;
import com.tracking.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bootstraps the single admin user from ADMIN_EMAIL/ADMIN_PASSWORD env vars
 * on first startup, since there is no self-service registration endpoint.
 */
@Configuration
public class AdminUserSeeder {

    @Bean
    CommandLineRunner seedAdminUser(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     @Value("${app.admin.email:}") String adminEmail,
                                     @Value("${app.admin.password:}") String adminPassword) {
        return args -> {
            if (adminEmail.isBlank() || adminPassword.isBlank() || userRepository.count() > 0) {
                return;
            }

            User user = new User();
            user.setEmail(adminEmail);
            user.setPasswordHash(passwordEncoder.encode(adminPassword));
            user.setName("Admin");
            userRepository.save(user);
        };
    }
}
