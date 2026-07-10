package com.arnav.corporatetrainingsystem;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController // this class has REST API endpoints
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;// Declares a private, final field for the repository that
                                                        // handles database operations.
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeRepository employeeRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {// Spring automatically provides an EmployeeRepository instance.
        this.employeeRepository = employeeRepository;// Stores it in the field so methods can use it.
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        if (employeeRepository.existsById(employee.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employee ID already exists.");
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already exists.");
        }

        if (userRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        Employee savedEmployee = employeeRepository.save(employee);

        User user = new User();
        user.setName(savedEmployee.getName());
        user.setEmail(savedEmployee.getEmail());
        user.setPassword(passwordEncoder.encode("employee123"));
        user.setRole(Role.EMPLOYEE);

        userRepository.save(user);

        return savedEmployee;
    }

    // get all employees
    @GetMapping("/employees") // gets requests to /employees
    public List<Employee> getEmployees() { // returns a list of all employees in the database
        return employeeRepository.findAll(); // gets all employees from database
    }

    // update an employee
    @PutMapping("/employees/{id}") // Endpoint to update an existing employee
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        // Takes the employee ID from the URL path and the updated employee data from
        // the request body,
        // then updates the corresponding employee in the database.
        Optional<Employee> employee = employeeRepository.findById(id);
        // finds the existing employee in the database by ID and returns it as an
        // Optional object, which may or may not
        // contain a value.
        if (employee.isPresent()) {// checks if the Optional contains a value (i.e., the employee was found in the
                                   // database)
        }
        Employee emp = employee.get();

        // Save old email before changing it
        String oldEmail = emp.getEmail();

        emp.setName(updatedEmployee.getName());
        emp.setEmail(updatedEmployee.getEmail());
        emp.setDepartment(updatedEmployee.getDepartment());
        emp.setTrainer(updatedEmployee.getTrainer());
        emp.setJoiningDate(updatedEmployee.getJoiningDate());
        emp.setTrainingDuration(updatedEmployee.getTrainingDuration());

        Employee savedEmployee = employeeRepository.save(emp);

        // Update corresponding user account
        Optional<User> user = userRepository.findByEmail(oldEmail);

        if (user.isPresent()) {

            User u = user.get();

            u.setName(savedEmployee.getName());
            u.setEmail(savedEmployee.getEmail());

            userRepository.save(u);
        }

        return savedEmployee;
    }

    // delete an employee
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            userRepository.findByEmail(employee.get().getEmail())
                    .ifPresent(userRepository::delete);

            employeeRepository.delete(employee.get());
        }
    }

    // logic to mark an employee's onboarding as complete
    @PutMapping("/employees/{id}/complete-onboarding")
    public Employee completeOnboarding(@PathVariable Long id) {// Takes the employee ID from the URL path and marks the
                                                               // onboarding process as complete for the corresponding
                                                               // employee.
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {// checks if the employee with the specified ID exists in the database
            Employee emp = employee.get(); // gets the actual Employee object from the Optional, allowing access to its
                                           // fields and methods.
            emp.setOnboardingCompleted(true);// sets the onboardingCompleted field of the employee to true, indicating
                                             // that the onboarding process is complete.
            return employeeRepository.save(emp);
        } else {
            return null;
        }
    }

    // logic to update an employee's profile
    @PutMapping("/employees/{id}/profile")
    public Employee updateProfile(@PathVariable Long id, @RequestBody Employee updatedEmployee) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            Employee emp = employee.get();

            // Employee can only update personal details
            emp.setName(updatedEmployee.getName());
            emp.setEmail(updatedEmployee.getEmail());

            return employeeRepository.save(emp);

        } else {

            return null;

        }
    }

    @GetMapping("/trainers")
    public List<Map<String, String>> getTrainers() {

        return userRepository.findByRole(Role.TRAINER)
                .stream()
                .map(user -> Map.of(
                        "name", user.getName(),
                        "email", user.getEmail()))
                .toList();
    }
}