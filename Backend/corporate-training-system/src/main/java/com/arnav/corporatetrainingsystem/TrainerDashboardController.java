package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin(origins = "*")
public class TrainerDashboardController {
    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    public TrainerDashboardController(EmployeeRepository employeeRepository, PerformanceReviewRepository performanceReviewRepository, QuizAttemptRepository quizAttemptRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @GetMapping("/trainer-dashboard/assigned-trainees")
    public long getAssignedTrainees(Authentication authentication) {
        String trainerEmail = authentication.getName(); // Replace with the logged-in trainer's name
        return employeeRepository.countByTrainer(trainerEmail);
    }

    @GetMapping("/trainer-dashboard/pending-reviews") // Endpoint to get the number of pending performance reviews for the logged-in trainer
    public int getPendingReviews(Authentication authentication) { // Returns the number of pending performance reviews for the logged-in trainer by comparing the list of employees assigned to the trainer with the list of performance reviews submitted today.
        String trainer = authentication.getName(); // Replace with the logged-in trainer's name LATER
        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainer); 
        // Get the list of employees assigned to the logged-in trainer by querying the EmployeeRepository for employees with the trainer's name
        int totalEmployees = assignedEmployees.size(); 
        // Get the total number of employees assigned to the logged-in trainer by getting the size of the list of assigned employees
        int reviewedToday=0;// Initialize a counter to keep track of the number of performance reviews submitted today by employees assigned to 
                        //the logged-in trainer
        List<PerformanceReview> reviews = performanceReviewRepository.findByReviewDate(LocalDate.now()); 
        // Get the list of performance reviews submitted today by querying the PerformanceReviewRepository for reviews with today's date
        for (PerformanceReview review : reviews) { // Iterate through the list of performance reviews submitted today
            for (Employee employee : assignedEmployees) {//For each performance review, iterate through the list of employees assigned to the logged-in trainer
                if (review.getEmployeeId() == employee.getId()) {
                //Check if the employee ID of the performance review matches the ID of any employee assigned to the logged-in trainer
                    reviewedToday++; // If a match is found, increment the count of reviews submitted today
                    break; // Break out of the inner loop since we found a match for this review
                }
            }
        }
        return totalEmployees - reviewedToday;// Calculate the number of pending reviews by subtracting the number of
                                              // reviews submitted today from the total number of employees
    }

    @GetMapping("/trainer-dashboard/pending-review-employees")// Endpoint to get the list of employees who have not submitted their performance reviews for the day
    public List<Employee> getPendingReviewEmployees(Authentication authentication) {// Returns a list of employees who have not submitted their performance reviews for the day by comparing the list of employees assigned to the logged-in trainer with the list of performance reviews submitted today.

        String trainer = authentication.getName(); // Replace with the logged-in trainer's name LATER

        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainer);
        // Get the list of employees assigned to the logged-in trainer by querying the EmployeeRepository for employees with the trainer's name
        List<PerformanceReview> reviews = performanceReviewRepository.findByReviewDate(java.time.LocalDate.now());
        // Get the list of performance reviews submitted today by querying the PerformanceReviewRepository for reviews with today's date
        List<Employee> pendingEmployees = new ArrayList<>();
        // Create a new list to store the employees who have not submitted their performance reviews for the day
        for (Employee employee : assignedEmployees) {// Iterate through the list of employees assigned to the logged-in trainer
            boolean reviewed = false;// Create a boolean variable to track whether the employee has submitted their performance review for the day
            for (PerformanceReview review : reviews) {// For each employee, iterate through the list of performance reviews submitted today
                if (review.getEmployeeId() == employee.getId()) {// Check if the employee ID of the performance review matches the ID of the employee
                    reviewed = true;//  If a match is found, set the reviewed variable to true to indicate that the employee has submitted their performance review for the day
                    break;
                }
            }
            if (!reviewed) {// If the employee has not submitted their performance review for the day, add them to the list of pending employees
                pendingEmployees.add(employee);// Add the employee to the list of pending employees
            }
        }
        return pendingEmployees;// Return the list of employees who have not submitted their performance reviews for the day
    }

    @GetMapping("/trainer-dashboard/quiz-performance-summary")
    public int getQuizPerformanceSummary(Authentication authentication) {
        String trainer = authentication.getName(); // Replace with the logged-in trainer's name LATER
        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainer); // Get the list of employees assigned to the logged-in trainer by querying the EmployeeRepository for employees with the trainer's name
        List<Long> employeeIds = assignedEmployees.stream()
                .map(employee -> employee.getId())
                .toList(); // Get the list of employee IDs assigned to the logged-in trainer by mapping the list of assigned employees to their IDs

        List<QuizAttempt> attempts = quizAttemptRepository.findByEmployeeIdIn(employeeIds);// Get all quiz attempts from the database by querying the QuizAttemptRepository for all quiz attempts

        if (attempts.isEmpty()) {// Check if there are no quiz attempts in the database
            return 0;
        }

        int totalScore = 0;

        for (QuizAttempt attempt : attempts) {// Iterate through the list of quiz attempts and sum up the scores of all attempts to calculate the total score
            totalScore += attempt.getScore();
        }

    return totalScore / attempts.size();
    }
}