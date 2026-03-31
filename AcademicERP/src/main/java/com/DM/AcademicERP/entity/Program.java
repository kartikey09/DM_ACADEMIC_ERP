package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "program", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"department_id", "name"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Program {

    public enum ProgramType {
        B_TECH, M_TECH, IM_TECH, PHD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long programId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProgramType type;

    @Column(name = "duration_years", nullable = false)
    private Integer durationYears;

    // Default constructor
    public Program() {}

    // All-args constructor
    public Program(Long programId, Department department, String name, ProgramType type, Integer durationYears) {
        this.programId = programId;
        this.department = department;
        this.name = name;
        this.type = type;
        this.durationYears = durationYears;
    }

    // Getters and Setters
    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProgramType getType() {
        return type;
    }

    public void setType(ProgramType type) {
        this.type = type;
    }

    public Integer getDurationYears() {
        return durationYears;
    }

    public void setDurationYears(Integer durationYears) {
        this.durationYears = durationYears;
    }
}
