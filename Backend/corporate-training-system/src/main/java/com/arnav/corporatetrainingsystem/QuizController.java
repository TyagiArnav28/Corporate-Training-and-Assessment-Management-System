package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizRepository quizRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // Create Quiz
    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {

        if (quizRepository.existsById(quiz.getId())) {
            throw new RuntimeException("Quiz ID already exists.");
        }

        return quizRepository.save(quiz);
    }

    // Get All Quizzes
    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    // Update Quiz
    @PutMapping("/quizzes/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {

        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isPresent()) {

            Quiz existingQuiz = quiz.get();

            existingQuiz.setQuizTitle(updatedQuiz.getQuizTitle());
            existingQuiz.setTimeLimit(updatedQuiz.getTimeLimit());
            existingQuiz.setPassingScore(updatedQuiz.getPassingScore());
            existingQuiz.setTrainingModuleId(updatedQuiz.getTrainingModuleId());
            existingQuiz.setQuizLink(updatedQuiz.getQuizLink());

            return quizRepository.save(existingQuiz);
        }

        return null;
    }

    // Delete Quiz
    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizRepository.deleteById(id);
    }
}
