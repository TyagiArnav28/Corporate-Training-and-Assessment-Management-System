package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {}
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