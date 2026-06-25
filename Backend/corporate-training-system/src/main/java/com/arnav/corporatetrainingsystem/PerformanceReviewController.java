package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a Spring REST controller
@CrossOrigin(origins = "*")
public class PerformanceReviewController {

    private final PerformanceReviewRepository performanceReviewRepository;// Injects the PerformanceReviewRepository dependency

    public PerformanceReviewController(PerformanceReviewRepository performanceReviewRepository) { // Constructor injection of the PerformanceReviewRepository dependency
        this.performanceReviewRepository = performanceReviewRepository;
    }

    @PostMapping("/performance-reviews") // Handles HTTP POST requests to create a new performance review
    public PerformanceReview addPerformanceReview(@RequestBody PerformanceReview performanceReview) {// Maps the request body to a PerformanceReview object
        return performanceReviewRepository.save(performanceReview);//Saves the performance review to the database
    }

    @GetMapping("/performance-reviews")// Handles HTTP GET requests to retrieve all performance reviews
    public List<PerformanceReview> getPerformanceReviews() {// Returns a list of all performance reviews in the database
        return performanceReviewRepository.findAll();// Retrieves all performance reviews from the database
    }

    @PutMapping("/performance-reviews/{id}") // Handles HTTP PUT requests to update an existing performance review by ID
    public PerformanceReview updatePerformanceReview(@PathVariable Long id, @RequestBody PerformanceReview updatedPerformanceReview) {
        Optional<PerformanceReview> performanceReview = performanceReviewRepository.findById(id);
        if (performanceReview.isPresent()) {
            PerformanceReview review = performanceReview.get();
            review.setEmployeeId(updatedPerformanceReview.getEmployeeId());
            review.setRating(updatedPerformanceReview.getRating());
            review.setComments(updatedPerformanceReview.getComments());
            review.setImprovementAreas(updatedPerformanceReview.getImprovementAreas());
            return performanceReviewRepository.save(review);
        } else {
            return null;
        }
    }

    @DeleteMapping("/performance-reviews/{id}")
    public void deletePerformanceReview(@PathVariable Long id) {
        performanceReviewRepository.deleteById(id);
    }
}