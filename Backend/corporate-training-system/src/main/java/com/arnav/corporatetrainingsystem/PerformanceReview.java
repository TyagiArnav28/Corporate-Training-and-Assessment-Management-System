package com.arnav.corporatetrainingsystem;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity // Marks this class as a JPA entity
@Table(name = "Performance_Reviews")
public class PerformanceReview {

    @Id
    private Long id;

    private long employeeId;

    private int rating;

    private String comments;

    private String improvementAreas;

    private LocalDate reviewDate;

    public PerformanceReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImprovementAreas() {
        return improvementAreas;
    }

    public void setImprovementAreas(String improvementAreas) {
        this.improvementAreas = improvementAreas;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
