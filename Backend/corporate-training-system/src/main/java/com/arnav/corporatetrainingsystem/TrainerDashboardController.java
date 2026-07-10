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
    public long getAssignedTrainees(@RequestParam String trainerEmail) {
        return employeeRepository.countByTrainer(trainerEmail);
    }

    @GetMapping("/trainer-dashboard/pending-reviews")
    public int getPendingReviews(@RequestParam String trainerEmail) {
        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainerEmail);
        int totalEmployees = assignedEmployees.size();
        int reviewedToday = 0;
        List<PerformanceReview> reviews = performanceReviewRepository.findByReviewDate(LocalDate.now());
        for (PerformanceReview review : reviews) {
            for (Employee employee : assignedEmployees) {
                if (review.getEmployeeId() == employee.getId()) {
                    reviewedToday++;
                    break;
                }
            }
        }
        return totalEmployees - reviewedToday;
    }

    @GetMapping("/trainer-dashboard/pending-review-employees")
    public List<Employee> getPendingReviewEmployees(@RequestParam String trainerEmail) {
        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainerEmail);
        List<PerformanceReview> reviews = performanceReviewRepository.findByReviewDate(java.time.LocalDate.now());
        List<Employee> pendingEmployees = new ArrayList<>();
        for (Employee employee : assignedEmployees) {
            boolean reviewed = false;
            for (PerformanceReview review : reviews) {
                if (review.getEmployeeId() == employee.getId()) {
                    reviewed = true;
                    break;
                }
            }
            if (!reviewed) {
                pendingEmployees.add(employee);
            }
        }
        return pendingEmployees;
    }

    @GetMapping("/trainer-dashboard/quiz-performance-summary")
    public int getQuizPerformanceSummary(@RequestParam String trainerEmail) {
        List<Employee> assignedEmployees = employeeRepository.findByTrainer(trainerEmail);
        List<Long> employeeIds = assignedEmployees.stream()
                .map(employee -> employee.getId())
                .toList();
        List<QuizAttempt> attempts = quizAttemptRepository.findByEmployeeIdIn(employeeIds);
        if (attempts.isEmpty()) {
            return 0;
        }
        int totalScore = 0;
        for (QuizAttempt attempt : attempts) {
            totalScore += attempt.getScore();
        }
        return totalScore / attempts.size();
    }
}