package com.arnav.corporatetrainingsystem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgressRepository extends JpaRepository<TrainingProgress, Long> {
    List<TrainingProgress> findByEmployeeId(Long employeeId);
}
//The Long type parameter indicates that the primary key of the TrainingModule entity is of type Long. */
/* Without writing any code, we get:
save()
findAll()
count()
findById()
deleteById()
*/