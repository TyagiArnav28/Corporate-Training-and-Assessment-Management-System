package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class TrainingModuleController {

    private final TrainingModuleRepository trainingModuleRepository;

    public TrainingModuleController(TrainingModuleRepository trainingModuleRepository) {
        this.trainingModuleRepository = trainingModuleRepository;
    }

    @PostMapping("/training-modules")
    public TrainingModule addTrainingModule(@RequestBody TrainingModule trainingModule) {
        return trainingModuleRepository.save(trainingModule);
    }

    @GetMapping("/training-modules")
    public List<TrainingModule> getTrainingModules() {
        return trainingModuleRepository.findAll();
    }

    @PutMapping("/training-modules/{id}")
    public TrainingModule updateTrainingModule(@PathVariable Long id, @RequestBody TrainingModule updatedTrainingModule) {
        Optional<TrainingModule> trainingModule = trainingModuleRepository.findById(id);
        if (trainingModule.isPresent()) {
            TrainingModule tm = trainingModule.get();
            tm.setModuleName(updatedTrainingModule.getModuleName());
            tm.setDescription(updatedTrainingModule.getDescription());
            tm.setMaterialLink(updatedTrainingModule.getMaterialLink());
            tm.setDeadline(updatedTrainingModule.getDeadline());
            tm.setAssignedQuiz(updatedTrainingModule.getAssignedQuiz());
            return trainingModuleRepository.save(tm);
        } else {
            return null;
        }
    }

    @DeleteMapping("/training-modules/{id}")
    public void deleteTrainingModule(@PathVariable Long id) {
        trainingModuleRepository.deleteById(id);
    }
}