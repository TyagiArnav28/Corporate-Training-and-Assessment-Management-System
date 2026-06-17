package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController // This is a placeholder controller for the dashboard. It can be expanded to include more functionality as needed.
@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
public class DashboardController {
    private final EmployeeRepository employeeRepository;

    public DashboardController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/employees/count") // Endpoint to get the total number of employees
    public int getEmployeeCount(){
        return (int) employeeRepository.count();
    }
    @GetMapping("/trainings/count") // Endpoint to get the total number of training programs
    public int getTrainingCount(){
        return 5;
    }
    @GetMapping("/completion-rate")
    public int getCompletionRate(){
        return 85; // Placeholder value for completion rate
    }
    @GetMapping("/underperforming/count")
    public int getUnderperformingCount(){
        return 10; // Placeholder value for underperforming employees
    }
}
