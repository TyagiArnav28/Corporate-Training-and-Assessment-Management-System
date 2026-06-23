package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PerformanceReviewController {

    private final PerformanceReviewRepository performanceReviewRepository;

    public PerformanceReviewController(PerformanceReviewRepository performanceReviewRepository) {
        this.performanceReviewRepository = performanceReviewRepository;
    }

    @PostMapping("/performance-reviews")
    public PerformanceReview addPerformanceReview(@RequestBody PerformanceReview performanceReview) {
        return performanceReviewRepository.save(performanceReview);
    }

    @GetMapping("/performance-reviews")
    public List<PerformanceReview> getPerformanceReviews() {
        return performanceReviewRepository.findAll();
    }

    @PutMapping("/performance-reviews/{id}")
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