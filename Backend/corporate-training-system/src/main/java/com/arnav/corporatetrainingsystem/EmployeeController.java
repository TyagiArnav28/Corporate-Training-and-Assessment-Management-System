package com.arnav.corporatetrainingsystem;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;//Declares a private, final field for the repository that handles database operations.

    public EmployeeController(EmployeeRepository employeeRepository) {// Spring automatically provides an EmployeeRepository instance.
        this.employeeRepository = employeeRepository;//Stores it in the field so methods can use it.
    }

    @PostMapping("/employees") // when someone sends data to this endpoint run this method
    public Employee addEmployee(@RequestBody Employee employee) {//Takes JSON from the request body and converts it into an Employee object.
        return employeeRepository.save(employee);//Saves the employee to the database.
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) { 

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            Employee emp = employee.get();

            emp.setName(updatedEmployee.getName());
            emp.setEmail(updatedEmployee.getEmail());
            emp.setDepartment(updatedEmployee.getDepartment());
            emp.setTrainer(updatedEmployee.getTrainer());
            emp.setJoiningDate(updatedEmployee.getJoiningDate());
            emp.setTrainingDuration(updatedEmployee.getTrainingDuration());

            return employeeRepository.save(emp);
        }

        return null;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

}