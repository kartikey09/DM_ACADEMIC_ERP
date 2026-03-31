package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_code", nullable = false, length = 20, unique = true)
    private String courseCode;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    // Default constructor
    public Course() {}

    // All-args constructor
    public Course(Long courseId, String courseCode, String title, Integer credits) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
