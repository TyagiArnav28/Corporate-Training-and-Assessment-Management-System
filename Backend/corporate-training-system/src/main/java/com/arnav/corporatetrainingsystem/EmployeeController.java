package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController //this class has REST API endpoints
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;//Declares a private, final field for the repository that handles database operations.

    public EmployeeController(EmployeeRepository employeeRepository) {// Spring automatically provides an EmployeeRepository instance.
        this.employeeRepository = employeeRepository;//Stores it in the field so methods can use it.
    }

    //create the employees
    @PostMapping("/employees") // when someone sends data to this endpoint run this method
    public Employee addEmployee(@RequestBody Employee employee) {//Takes JSON from the request body and converts it into an Employee object.
        return employeeRepository.save(employee);//Saves the employee to the database.
    }

    //get all employees
    @GetMapping("/employees") // gets requests to /employees
    public List<Employee> getEmployees() { //returns a list of all employees in the database
        return employeeRepository.findAll(); //gets all employees from database
    }

    //update an employee
    @PutMapping("/employees/{id}")// Endpoint to update an existing employee
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) { 
// Takes the employee ID from the URL path and the updated employee data from the request body, 
// then updates the corresponding employee in the database.
        Optional<Employee> employee = employeeRepository.findById(id);
        //finds the existing employee in the database by ID and returns it as an Optional object, which may or may not 
        // contain a value.
        if (employee.isPresent()) {// checks if the Optional contains a value (i.e., the employee was found in the database)

            Employee emp = employee.get();// gets the actual Employee object from the Optional, allowing access to its fields and methods.

            emp.setName(updatedEmployee.getName());// updates the name of the existing employee with the new name provided in the updatedEmployee object.
            emp.setEmail(updatedEmployee.getEmail());// updates the email of the existing employee with the new email provided in the updatedEmployee object.
            emp.setDepartment(updatedEmployee.getDepartment());// updates the department of the existing employee with the new department provided in the updatedEmployee object.
            emp.setTrainer(updatedEmployee.getTrainer());// updates the trainer of the existing employee with the new trainer provided in the updatedEmployee object.
            emp.setJoiningDate(updatedEmployee.getJoiningDate());// updates the joining date of the existing employee with the new joining date provided in the updatedEmployee object.
            emp.setTrainingDuration(updatedEmployee.getTrainingDuration());// updates the training duration of the existing employee with the new training duration provided in the updatedEmployee object.

            return employeeRepository.save(emp);// saves the updated employee back to the database and returns the updated employee object.
        }

        return null;// returns null if the employee with the specified ID was not found in the database.
    }

    //delete an employee
    @DeleteMapping("/employees/{id}")// Endpoint to delete an employee by ID
    public void deleteEmployee(@PathVariable Long id) {// Takes the employee ID from the URL path and deletes the corresponding employee from the database.
        employeeRepository.deleteById(id);// deletes the employee with the specified ID from the database.
    }

    //logic to mark an employee's onboarding as complete
    @PutMapping("/employees/{id}/complete-onboarding")
    public Employee completeOnboarding(@PathVariable Long id) {// Takes the employee ID from the URL path and marks the onboarding process as complete for the corresponding employee.
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {// checks if the employee with the specified ID exists in the database
            Employee emp = employee.get(); // gets the actual Employee object from the Optional, allowing access to its fields and methods.
            emp.setOnboardingCompleted(true);// sets the onboardingCompleted field of the employee to true, indicating that the onboarding process is complete.
        return employeeRepository.save(emp);
        } else {
            return null;
        }
    }

    //logic to update an employee's profile
    @PutMapping("/employees/{id}/profile")
    public Employee updateProfile(@PathVariable Long id, @RequestBody Employee updatedEmployee) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            Employee emp = employee.get();

            // Employee can only update personal details
            emp.setName(updatedEmployee.getName());
            emp.setEmail(updatedEmployee.getEmail());

        return employeeRepository.save(emp);

        } else {

            return null;

        }
    }
}