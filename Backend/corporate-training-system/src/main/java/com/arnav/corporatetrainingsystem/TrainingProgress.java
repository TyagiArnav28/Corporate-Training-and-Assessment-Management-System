package com.arnav.corporatetrainingsystem;

import jakarta.persistence.*;

@Entity
@Table(name = "training_progress")
public class TrainingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private Long trainingModuleId;

    private String assignmentLink;

    private boolean completed;

    public TrainingProgress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(Long trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }

    public String getAssignmentLink() {
        return assignmentLink;
    }

    public void setAssignmentLink(String assignmentLink) {
        this.assignmentLink = assignmentLink;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
