package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class QuizAttemptController {

    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptController(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    // Create Quiz Attempt
    @PostMapping("/quiz-attempts")
    public QuizAttempt addQuizAttempt(@RequestBody QuizAttempt quizAttempt) {
        quizAttempt.setStatus("PENDING");
        return quizAttemptRepository.save(quizAttempt);
    }

    // Get All Quiz Attempts
    @GetMapping("/quiz-attempts")
    public List<QuizAttempt> getQuizAttempts() {
        return quizAttemptRepository.findAll();
    }

    // Update Quiz Attempt
    @PutMapping("/quiz-attempts/{id}")
    public QuizAttempt updateQuizAttempt(@PathVariable Long id,@RequestBody QuizAttempt updatedQuizAttempt) {

        Optional<QuizAttempt> quizAttempt = quizAttemptRepository.findById(id);

        if (quizAttempt.isPresent()) {

            QuizAttempt existingAttempt = quizAttempt.get();

            existingAttempt.setEmployeeId(updatedQuizAttempt.getEmployeeId());
            existingAttempt.setQuizId(updatedQuizAttempt.getQuizId());
            existingAttempt.setScore(updatedQuizAttempt.getScore());
            existingAttempt.setPassed(updatedQuizAttempt.isPassed());
            existingAttempt.setAttemptDate(updatedQuizAttempt.getAttemptDate());
            existingAttempt.setFeedback(updatedQuizAttempt.getFeedback());
            existingAttempt.setStatus("EVALUATED");

            return quizAttemptRepository.save(existingAttempt);
        }

        return null;
    }

    // Delete Quiz Attempt
    @DeleteMapping("/quiz-attempts/{id}")
    public void deleteQuizAttempt(@PathVariable Long id) {
        quizAttemptRepository.deleteById(id);
    }
}