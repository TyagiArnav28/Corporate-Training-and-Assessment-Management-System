package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}