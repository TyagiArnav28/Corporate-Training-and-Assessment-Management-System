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
        // Takes the performance review ID from the URL path and the updated performance review data from the request body, 
        // then updates the corresponding performance review in the database.
        Optional<PerformanceReview> performanceReview = performanceReviewRepository.findById(id);
        // Finds the existing performance review in the database by ID and returns it as an Optional object, 
        // which may or may not contain a value
        if (performanceReview.isPresent()) {// Checks if the performance review with the specified ID exists
            PerformanceReview review = performanceReview.get();//getter method gets the existing performance review from the Optional object
            review.setEmployeeId(updatedPerformanceReview.getEmployeeId());//setter method updates the employee ID of the existing performance review with the new value from the updated performance review
            review.setRating(updatedPerformanceReview.getRating());//setter method updates the rating of the existing performance review with the new value from the updated performance review
            review.setComments(updatedPerformanceReview.getComments());//setter method updates the comments of the existing performance review with the new value from the updated performance review
            review.setImprovementAreas(updatedPerformanceReview.getImprovementAreas());//setter method updates the improvement areas of the existing performance review with the new value from the updated performance review
            review.setReviewDate(updatedPerformanceReview.getReviewDate());//setter method updates the review date of the existing performance review with the new value from the updated performance review
            return performanceReviewRepository.save(review);//Saves the updated performance review back to the database and returns it.
        } else {
            return null;//If the performance review with the specified ID does not exist, return null.
        }
    }

    @DeleteMapping("/performance-reviews/{id}")// Endpoint to delete a performance review by ID
    public void deletePerformanceReview(@PathVariable Long id) {// Takes the performance review ID from the URL path and deletes the corresponding performance review from the database.
        performanceReviewRepository.deleteById(id);//Deletes the performance review with the specified ID from the database.
    }
}