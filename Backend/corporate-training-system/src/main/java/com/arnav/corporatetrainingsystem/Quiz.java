package com.arnav.corporatetrainingsystem;

import jakarta.persistence.*;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    private Long id;

    private String quizTitle;

    private int timeLimit;

    private int passingScore;

    private Long trainingModuleId;

    private String quizLink;

    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public Long getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(Long trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }

    public String getQuizLink() {
        return quizLink;
    }

    public void setQuizLink(String quizLink) {
        this.quizLink = quizLink;
    }
}