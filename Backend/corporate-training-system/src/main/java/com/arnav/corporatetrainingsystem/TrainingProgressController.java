package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TrainingProgressController {

    private final TrainingProgressRepository trainingProgressRepository;

    public TrainingProgressController(TrainingProgressRepository trainingProgressRepository) {
        this.trainingProgressRepository = trainingProgressRepository;
    }

    @PostMapping("/training-progress")
    public TrainingProgress addTrainingProgress(@RequestBody TrainingProgress trainingProgress) {
        return trainingProgressRepository.save(trainingProgress);
    }

    @GetMapping("/training-progress")
    public List<TrainingProgress> getTrainingProgresses() {
        return trainingProgressRepository.findAll();
    }

    @PutMapping("/training-progress/{id}/complete")
    public TrainingProgress markComplete(@PathVariable Long id) {

        Optional<TrainingProgress> progress = trainingProgressRepository.findById(id);

        if (progress.isPresent()) {

            TrainingProgress trainingProgress = progress.get();

            trainingProgress.setCompleted(true);

            return trainingProgressRepository.save(trainingProgress);
        }

        return null;
    }
}