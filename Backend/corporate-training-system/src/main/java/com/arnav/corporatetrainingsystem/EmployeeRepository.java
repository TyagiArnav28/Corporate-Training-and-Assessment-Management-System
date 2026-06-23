package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {   
}
// Means I want a repository for Employee objects whose primary key type is Long
/* without writing a single method spring JPA gives 
    employeeRepository.save(employee);
    employeeRepository.findAll();
    employeeRepository.findById(1L);
    employeeRepository.count();
    employeeRepository.deleteById(1L);
*/