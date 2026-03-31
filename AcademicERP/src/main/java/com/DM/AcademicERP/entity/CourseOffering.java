package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "course_offering", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"course_id", "semester_id"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offering_id")
    private Long offeringId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @Column(name = "schedule_days", nullable = false, length = 20)
    private String scheduleDays;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity = 60;

    @Column(name = "enrolled_count", nullable = false)
    private Integer enrolledCount = 0;

    // Default constructor
    public CourseOffering() {}

    // All-args constructor
    public CourseOffering(Long offeringId, Course course, Semester semester, String scheduleDays, 
                         LocalTime startTime, Integer maxCapacity, Integer enrolledCount) {
        this.offeringId = offeringId;
        this.course = course;
        this.semester = semester;
        this.scheduleDays = scheduleDays;
        this.startTime = startTime;
        this.maxCapacity = maxCapacity;
        this.enrolledCount = enrolledCount;
    }

    // Getters and Setters
    public Long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Long offeringId) {
        this.offeringId = offeringId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(String scheduleDays) {
        this.scheduleDays = scheduleDays;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(Integer enrolledCount) {
        this.enrolledCount = enrolledCount;
    }
}
