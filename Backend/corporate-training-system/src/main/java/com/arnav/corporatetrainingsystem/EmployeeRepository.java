package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long countByTrainer(String trainer);
    //counts how many employees are assigned to a particular trainer.
    //Will incorporate this method later while creating the login logic for the trainer.
    List<Employee> findByTrainer(String trainer);//returns all employees assigned to a particular trainer.
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    }
/* This interface extends JpaRepository, which performes all the database operations for the TrainingModule entity. 
The JpaRepository provides methods for saving, finding, updating, and deleting entities. 
The Long type parameter indicates that the primary key of the TrainingModule entity is of type Long. */
/* without writing a single method spring JPA gives 
    employeeRepository.save(employee);
    employeeRepository.findAll();
    employeeRepository.findById(1L);
    employeeRepository.count();
    employeeRepository.deleteById(1L);
*/