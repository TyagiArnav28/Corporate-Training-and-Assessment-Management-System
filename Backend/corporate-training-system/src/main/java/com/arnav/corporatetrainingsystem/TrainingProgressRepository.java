package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgressRepository extends JpaRepository<TrainingProgress, Long> {}
/* This interface extends JpaRepository, which performes all the database operations for the TrainingModule entity. 
The JpaRepository provides methods for saving, finding, updating, and deleting entities. 
The Long type parameter indicates that the primary key of the TrainingModule entity is of type Long. */
/* Without writing any code, we get:
save()
findAll()
count()
findById()
deleteById()
*/