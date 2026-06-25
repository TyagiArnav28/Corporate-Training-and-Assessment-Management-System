package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController //this class has REST API endpoints
@CrossOrigin(origins = "*")
public class TrainingModuleController {

    private final TrainingModuleRepository trainingModuleRepository; //Declares a private, final field for the repository that handles database operations.

    public TrainingModuleController(TrainingModuleRepository trainingModuleRepository) { // Spring automatically provides a TrainingModuleRepository instance.
        this.trainingModuleRepository = trainingModuleRepository;//Stores it in the field so methods can use it.
    }

    @PostMapping("/training-modules") // when someone sends data to this endpoint run this method
    public TrainingModule addTrainingModule(@RequestBody TrainingModule trainingModule) {//Takes JSON from the request body and converts it into a TrainingModule object.
        return trainingModuleRepository.save(trainingModule);//Saves the training module to the database.
    }

    @GetMapping("/training-modules") // gets requests to /training-modules

    public List<TrainingModule> getTrainingModules() {//returns a list of all training modules in the database
        return trainingModuleRepository.findAll();//gets all training modules from database
    }

    @PutMapping("/training-modules/{id}")// Endpoint to update an existing training module
    public TrainingModule updateTrainingModule(@PathVariable Long id, @RequestBody TrainingModule updatedTrainingModule) {
        // Takes the training module ID from the URL path and the updated training module data from the request body, 
        // then updates the corresponding training module in the database.
        Optional<TrainingModule> trainingModule = trainingModuleRepository.findById(id);
        //finds the existing training module in the database by ID and returns it as an Optional object, 
        // which may or may not contain a value
        if (trainingModule.isPresent()) {
            TrainingModule tm = trainingModule.get();//getter method gets the existing training module from the Optional object
            tm.setModuleName(updatedTrainingModule.getModuleName());//setter method updates the module name of the existing training module with the new value from the updated training module
            tm.setDescription(updatedTrainingModule.getDescription());//setter method updates the description of the existing training module with the new value from the updated training module
            tm.setMaterialLink(updatedTrainingModule.getMaterialLink());//setter method updates the material link of the existing training module with the new value from the updated training module
            tm.setDeadline(updatedTrainingModule.getDeadline());//setter method updates the deadline of the existing training module with the new value from the updated training module
            tm.setAssignedQuiz(updatedTrainingModule.getAssignedQuiz());//setter method updates the assigned quiz of the existing training module with the new value from the updated training module
            return trainingModuleRepository.save(tm);//Saves the updated training module back to the database and returns it.
        } else {
            return null;//If the training module with the specified ID does not exist, return null.
        }
    }

    @DeleteMapping("/training-modules/{id}")// Endpoint to delete a training module by ID
    public void deleteTrainingModule(@PathVariable Long id) {// Takes the training module ID from the URL path and deletes the corresponding training module from the database.
        trainingModuleRepository.deleteById(id);//Deletes the training module with the specified ID from the database.
    }
}