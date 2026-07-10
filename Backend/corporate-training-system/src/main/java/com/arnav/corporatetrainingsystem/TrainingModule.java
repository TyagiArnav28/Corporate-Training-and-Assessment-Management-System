package com.arnav.corporatetrainingsystem;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity //Indicates that this class is an entity and is mapped to a database table.
@Table(name = "training_modules")
public class TrainingModule {

    @Id //Specifies the primary key of an entity.
    private Long id;

    private String moduleName;

    private String description;

    private String materialLink;

    private LocalDate deadline;

    private String assignedQuiz;

    public TrainingModule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialLink() {
        return materialLink;
    }

    public void setMaterialLink(String materialLink) {
        this.materialLink = materialLink;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getAssignedQuiz() {
        return assignedQuiz;
    }

    public void setAssignedQuiz(String assignedQuiz) {
        this.assignedQuiz = assignedQuiz;
    }
}
