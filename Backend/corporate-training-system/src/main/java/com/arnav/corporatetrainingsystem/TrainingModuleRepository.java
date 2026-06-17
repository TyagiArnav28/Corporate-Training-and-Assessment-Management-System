package com.arnav.corporatetrainingsystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingModuleRepository
        extends JpaRepository<TrainingModule, Long> {

}

/* Without writing any code, we get:
save()
findAll()
count()
findById()
deleteById()
*/