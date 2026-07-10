package com.arnav.corporatetrainingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Create Admin Account
        if (userRepository.findByEmail("admin@cts.com").isEmpty()) {

            User admin = new User();
            admin.setEmail("admin@cts.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }

        // Create Trainer Account
        if (userRepository.findByEmail("rahul@cts.com").isEmpty()) {

            User trainer = new User();
            trainer.setEmail("rahul@cts.com");
            trainer.setName("Rahul Sharma");
            trainer.setPassword(passwordEncoder.encode("trainer123"));
            trainer.setRole(Role.TRAINER);

            userRepository.save(trainer);
        }
        // Create Trainer Account - Amit Kumar
        if (userRepository.findByEmail("amit@cts.com").isEmpty()) {

            User trainer = new User();
            trainer.setEmail("amit@cts.com");
            trainer.setName("Amit Kumar");
            trainer.setPassword(passwordEncoder.encode("trainer123"));
            trainer.setRole(Role.TRAINER);

            userRepository.save(trainer);
        }

        // Create Trainer Account - Priya Singh
        if (userRepository.findByEmail("priya@cts.com").isEmpty()) {

            User trainer = new User();
            trainer.setEmail("priya@cts.com");
            trainer.setName("Priya Singh");
            trainer.setPassword(passwordEncoder.encode("trainer123"));
            trainer.setRole(Role.TRAINER);

            userRepository.save(trainer);
        }
        // Create Employee Account
        if (userRepository.findByEmail("employee@cts.com").isEmpty()) {

            User employee = new User();
            employee.setEmail("employee@cts.com");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setRole(Role.EMPLOYEE);

            userRepository.save(employee);
        }
    }
}