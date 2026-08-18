package com.codder.stayease.config;

import com.codder.stayease.entity.User;
import com.codder.stayease.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    // =====================================================
    // CHANGE THESE BEFORE FIRST DEPLOYMENT
    // (or better: move to environment variables — see note below)
    // =====================================================

    private static final String ADMIN_NAME = "Admin";
    private static final String ADMIN_EMAIL = "admin@stayease.com";
    private static final String ADMIN_PASSWORD = "ChangeMe123!";
    private static final String ADMIN_PHONE = "9999999999";
    private static final String ADMIN_ROLE = "ADMIN";


    @Bean
    CommandLineRunner seedAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.existsByEmail(ADMIN_EMAIL)) {
                // Admin already exists — nothing to do.
                return;
            }

            User admin = new User(
                    ADMIN_NAME,
                    ADMIN_EMAIL,
                    passwordEncoder.encode(ADMIN_PASSWORD),
                    ADMIN_ROLE,
                    ADMIN_PHONE
            );

            admin.setEnabled(true);

            userRepository.save(admin);

            System.out.println(
                    "=================================================="
            );
            System.out.println(
                    " Default admin account created:"
            );
            System.out.println(
                    " Email:    " + ADMIN_EMAIL
            );
            System.out.println(
                    " Password: " + ADMIN_PASSWORD
            );
            System.out.println(
                    " >>> Log in and change this password immediately. <<<"
            );
            System.out.println(
                    "=================================================="
            );
        };
    }
}