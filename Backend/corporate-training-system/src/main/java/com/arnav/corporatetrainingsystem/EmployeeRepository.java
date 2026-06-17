package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {   
}
// This interface extends JpaRepository, which provides CRUD operations for the Employee entity. The first generic parameter is the type of the entity, and the second is the type of the entity's primary key.