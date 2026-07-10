package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeDashboardController {
    private final EmployeeRepository employeeRepository;
    private final TrainingProgressRepository trainingProgressRepository;
    // Repository to access training progress data from the database since course
    // progress needs it.
    private final PerformanceReviewRepository performanceReviewRepository;// for feedback and performance review data,
                                                                          // which is also needed for the employee
                                                                          // dashboard.
    private final QuizAttemptRepository quizAttemptRepository;// for quiz attempt data, which is also needed for the
                                                              // employee dashboard.

    public EmployeeDashboardController(TrainingProgressRepository trainingProgressRepository,
            PerformanceReviewRepository performanceReviewRepository, QuizAttemptRepository quizAttemptRepository,
            EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.trainingProgressRepository = trainingProgressRepository;
        this.performanceReviewRepository = performanceReviewRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @GetMapping("/employee-dashboard/course-progress")
    public int getCourseProgress(Authentication authentication) {
        String email = authentication.getName(); // Get the email of the logged-in employee from the authentication
                                                 // object
        Employee employee = employeeRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        long employeeId = employee.getId(); // Get the ID of the logged-in employee
        // Get the total number of training modules and the number of completed modules
        // from the TrainingProgressRepository, then calculate and return the course
        // progress percentage for the logged-in employee.
        long totalModules = trainingProgressRepository
                .findByEmployeeId(employeeId)
                .size();
        // Get the total number of training modules by counting all entries in the
        // TrainingProgressRepository
        long completedModules = trainingProgressRepository
                .findByEmployeeId(employeeId)
                .stream()
                .filter(progress -> progress.isCompleted())
                .count();
        // Get the number of completed training modules by filtering the list of all
        // training progress entries to only include those that are marked as completed,
        // then counting the resulting list
        if (totalModules == 0) {
            return 0;
        }
        // Check if there are no training modules to avoid division by zero, and return
        // 0% progress if that's the case
        return (int) ((completedModules * 100) / totalModules);
        // Calculate the course progress percentage by dividing the number of completed
        // modules by the total number of modules, multiplying by 100, and casting the
        // result to an integer
    }// Later, when we implement login, we'll filter it by employee, exactly like in
     // the Trainer Dashboard

    @GetMapping("/employee-dashboard/feedback") // Endpoint to get the latest performance review for the logged-in
                                                // employee
    public PerformanceReview getFeedback(Authentication authentication) {
        String email = authentication.getName(); // Get the email of the logged-in employee from the authentication
                                                 // object
        Employee employee = employeeRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Employee not found"));
        // Get the latest performance review for the logged-in employee by querying the
        // PerformanceReviewRepository for all reviews,
        // then returning the most recent one based on the review date.
        List<PerformanceReview> reviews = performanceReviewRepository.findByEmployeeId(employee.getId());
        // Get the list of all performance reviews by querying the
        // PerformanceReviewRepository for all entries
        if (reviews.isEmpty()) {// Check if there are no performance reviews to avoid an
                                // IndexOutOfBoundsException, and return null if that's the case
            return null;
        }

        return reviews.get(reviews.size() - 1);
        // Return the latest performance review by getting the last entry in the list of
        // reviews, which is assumed to be sorted by review date
        // in ascending order

    }// Later, when we implement login, we'll filter it by employee, exactly like in
     // the Trainer Dashboard

    @GetMapping("/employee-dashboard/score-history")
    public List<QuizAttempt> getScoreHistory(Authentication authentication) {
        String email = authentication.getName(); // Get the email of the logged-in employee from the authentication
                                                 // object
        Employee employee = employeeRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Employee not found"));
        // Find the employee in the database using their
        return quizAttemptRepository.findByEmployeeId(employee.getId());// Query the QuizAttemptRepository for all quiz
                                                                        // attempts made by the logged-in employee, and
                                                                        // return the list of attempts
    }
}