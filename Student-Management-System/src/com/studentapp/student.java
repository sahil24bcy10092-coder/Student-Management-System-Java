package com.Studentapp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String regNo;
    private String department;
    private Map<String, Double> courseGrades;

    public Student(String name, String regNo, String department) {
        this.name = name;
        this.regNo = regNo;
        this.department = department;
        this.courseGrades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Map<String, Double> getCourseGrades() {
        return courseGrades;
    }

    public void addGrade(String courseName, double grade) {
        courseGrades.put(courseName, grade);
    }

    public double calculateGPA() {
        if (courseGrades.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double g : courseGrades.values()) {
            total += g;
        }
        return total / courseGrades.size();
    }

    @Override
    public String toString() {
        return String.format("Reg No: %-12s | Name: %-18s | Dept: %-8s | GPA: %.2f", 
                regNo, name, department, calculateGPA());
    }
}