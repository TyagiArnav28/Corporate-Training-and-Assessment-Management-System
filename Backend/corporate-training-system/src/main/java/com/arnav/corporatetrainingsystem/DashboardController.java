package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController // This class cointains REST API endpoints for the dashboard
@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
public class DashboardController {
    private final EmployeeRepository employeeRepository; // This is a dependency that provides access to employee data in the database.
    private final TrainingModuleRepository trainingModuleRepository;
    private final TrainingProgressRepository trainingProgressRepository;
    private final PerformanceReviewRepository performanceReviewRepository;

    public DashboardController(
            EmployeeRepository employeeRepository, //Spring automatically provides the required dependency through the class’s constructor
            TrainingModuleRepository trainingModuleRepository,
            TrainingProgressRepository trainingProgressRepository,
            PerformanceReviewRepository performanceReviewRepository) {
        this.employeeRepository = employeeRepository; // stores that injected dependency into the controller’s field so it can be used later in methods.
        this.trainingModuleRepository = trainingModuleRepository;
        this.trainingProgressRepository = trainingProgressRepository;
        this.performanceReviewRepository = performanceReviewRepository;
    }

    @GetMapping("/employees/count") // Endpoint to get the total number of employees
    public int getEmployeeCount() {
        return (int) employeeRepository.count();
    }

    @GetMapping("/trainings/count") // Endpoint to get the total number of training programs
    public int getTrainingCount() {
        return (int) trainingModuleRepository.count();
    }

    @GetMapping("/completion-rate")
    public int getCompletionRate() {

        long totalProgress = trainingProgressRepository.count();

        long completedCount = trainingProgressRepository.findAll()
                .stream()
                .filter(TrainingProgress::isCompleted)
                .count();

        if (totalProgress == 0) {
            return 0;
        }

        return (int) ((completedCount * 100)
                / totalProgress);
    }

    @GetMapping("/underperforming/count")
    public int getUnderperformingCount() {

        return (int) performanceReviewRepository.findAll()
                .stream()
                .filter(review -> review.getRating() < 3)
                .count();
    }
}

