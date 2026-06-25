package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController // Indicates that this class is a REST controller that handles HTTP requests and responses
@CrossOrigin(origins = "*") // Allows cross-origin requests from any domain, enabling the frontend to communicate with the backend
public class TrainingProgressController {

    private final TrainingProgressRepository trainingProgressRepository; // Injects the TrainingProgressRepository dependency into the controller, allowing it to perform database operations on the TrainingProgress entity

    public TrainingProgressController(TrainingProgressRepository trainingProgressRepository) {
        this.trainingProgressRepository = trainingProgressRepository; // Constructor that initializes the TrainingProgressRepository dependency
    }

    @PostMapping("/training-progress") // Handles HTTP POST requests to the endpoint for creating a new TrainingProgress entity
    public TrainingProgress addTrainingProgress(@RequestBody TrainingProgress trainingProgress) { // The @RequestBody annotation indicates that the request body will be deserialized into a TrainingProgress object, which will be saved to the database
        return trainingProgressRepository.save(trainingProgress);// Saves the new TrainingProgress entity to the database and returns the saved entity
    }

    @GetMapping("/training-progress") // Handles HTTP GET requests to the endpoint for retrieving all TrainingProgress entities

    public List<TrainingProgress> getTrainingProgresses() {
        return trainingProgressRepository.findAll(); // Retrieves all TrainingProgress entities from the database and returns them as a list
    }

    @PutMapping("/training-progress/{id}")
    public TrainingProgress updateTrainingProgress(
            @PathVariable Long id,
            @RequestBody TrainingProgress updatedTrainingProgress) {

        Optional<TrainingProgress> progress = trainingProgressRepository.findById(id);

        if (progress.isPresent()) {

            TrainingProgress trainingProgress = progress.get();

            trainingProgress.setAssignmentLink(
                    updatedTrainingProgress.getAssignmentLink());

            trainingProgress.setCompleted(
                    updatedTrainingProgress.isCompleted());

            return trainingProgressRepository.save(
                    trainingProgress);
        }

        return null;
    }

    @PutMapping("/training-progress/{id}/complete") // Handles PUT requests to the endpoint for updating the completion status of a TrainingProgress entity
    public TrainingProgress markComplete(@PathVariable Long id) {
        //when spring receives /training-progress/1/complete it takes 1 and stores it in long id so id=1
        Optional<TrainingProgress> progress = trainingProgressRepository.findById(id);
        // this asks find the row with id=1 and return it as an optional object. If the row is not found, it will return an empty optional object.
        if (progress.isPresent()) {

            TrainingProgress trainingProgress = progress.get();//gets the TrainingProgress object from the optional object.

            trainingProgress.setCompleted(true);// The completion status of the TrainingProgress entity is updated to true, indicating that the training module has been completed by the employee

            return trainingProgressRepository.save(trainingProgress);// The updated TrainingProgress entity is saved back to the database, and the saved entity is returned as the response
        }

        return null; // If the TrainingProgress entity with the specified ID is not found, null is returned as the response
    }

    @DeleteMapping("/training-progress/{id}")  // Handles HTTP DELETE requests to the endpoint for deleting a TrainingProgress entity
    public void deleteTrainingProgress(@PathVariable Long id) {
        trainingProgressRepository.deleteById(id);
    }
}