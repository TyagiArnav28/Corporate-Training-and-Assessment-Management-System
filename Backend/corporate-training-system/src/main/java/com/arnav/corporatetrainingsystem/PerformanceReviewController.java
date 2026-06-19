package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}