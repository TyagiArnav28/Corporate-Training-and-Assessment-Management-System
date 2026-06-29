package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController // This class cointains REST API endpoints for the dashboard
@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
public class AdminDashboardController {
    private final EmployeeRepository employeeRepository; // This is a dependency that provides access to employee data in the database.
    private final TrainingModuleRepository trainingModuleRepository;// This is a dependency that provides access to training module data in the database.
    private final TrainingProgressRepository trainingProgressRepository;// This is a dependency that provides access to training progress data in the database.
    private final PerformanceReviewRepository performanceReviewRepository;// This is a dependency that provides access to performance review data in the database.

    public AdminDashboardController(
            EmployeeRepository employeeRepository, //Spring automatically provides the required dependency through the class’s constructor
            TrainingModuleRepository trainingModuleRepository,//Spring automatically provides the required dependency through the class’s constructor
            TrainingProgressRepository trainingProgressRepository,//Spring automatically provides the required dependency through the class’s constructor
            PerformanceReviewRepository performanceReviewRepository) {//Spring automatically provides the required dependency through the class’s constructor
        this.employeeRepository = employeeRepository; // stores that injected dependency into the controller’s field so it can be used later in methods.
        this.trainingModuleRepository = trainingModuleRepository;// stores that injected dependency into the controller’s field so it can be used later in methods.
        this.trainingProgressRepository = trainingProgressRepository;// stores that injected dependency into the controller’s field so it can be used later in methods.
        this.performanceReviewRepository = performanceReviewRepository;// stores that injected dependency into the controller’s field so it can be used later in methods.
    }

    @GetMapping("/employees/count") // Endpoint to get the total number of employees
    public int getEmployeeCount() {
        return (int) employeeRepository.count();// Uses the employeeRepository to count the total number of employees in the database and returns that count as an integer.
    }

    @GetMapping("/trainings/count") // Endpoint to get the total number of training programs
    public int getTrainingCount() {
        return (int) trainingModuleRepository.count();// Uses the trainingModuleRepository to count the total number of training programs in the database and returns that count as an integer.
    }

    @GetMapping("/completion-rate")
    public int getCompletionRate() {

        long totalProgress = trainingProgressRepository.count(); //counts total number of training progress records in the database through the trainingProgressRepository

        long completedCount = trainingProgressRepository.findAll() // retrieves all training progress records from the database through the trainingProgressRepository
                .stream() // converts the list of training progress records into a stream, allowing for functional-style operations on the data
                .filter(TrainingProgress::isCompleted)// filters the stream to include only those training progress records where the isCompleted method returns true, indicating that the training module has been completed by the employee
                .count();// counts the number of training progress records that passed the filter, resulting in the total number of completed training modules

        if (totalProgress == 0) {// checks if there are no training progress records to avoid division by zero
            return 0;
        }

        return (int) ((completedCount * 100)// calculates the completion rate as a percentage by multiplying the number of completed training modules by 100 and dividing it by the total number of training progress records. The result is cast to an integer before being returned.
                / totalProgress);
    }

    @GetMapping("/underperforming/count")// Endpoint to get the count of underperforming employees
    public int getUnderperformingCount() {

        return (int) performanceReviewRepository.findAll()// retrieves all performance review records from the database through the performanceReviewRepository
                .stream()// converts the list of performance review records into a stream, allowing for functional-style operations on the data
                .filter(review -> review.getRating() < 3)
                /* filters the stream to include only those performance review records where the rating is less than 3, 
                    indicating that the employee is underperforming*/
                .count();// counts the number of performance review records that passed the filter, resulting in the total number of underperforming employees
    }
}

