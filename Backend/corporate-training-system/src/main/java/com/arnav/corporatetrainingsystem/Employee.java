package com.arnav.corporatetrainingsystem;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "employees") // This annotation specifies the name of the database table to be used for mapping
public class Employee {

    @Id // This annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation provides the specification of generation strategies for the values of primary keys
    private Long id;

    private String name;

    private String email;

    private String department;

    private String trainer;

    private LocalDate joiningDate;

    private Integer trainingDuration;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Integer getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(Integer trainingDuration) {
        this.trainingDuration = trainingDuration;
    }
}