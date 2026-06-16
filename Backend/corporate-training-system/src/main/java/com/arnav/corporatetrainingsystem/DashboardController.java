package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController // This is a placeholder controller for the dashboard. It can be expanded to include more functionality as needed.
@CrossOrigin(origins = "*") // Allow cross-origin requests from any origin
public class DashboardController {
    @GetMapping("/employees/count") // Endpoint to get the total number of employees
    public int getEmployeeCount(){
        return 100;
    }
}
