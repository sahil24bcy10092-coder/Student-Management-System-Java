package com.studentapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManager() {
        this.students = new ArrayList<>();
        loadData(); // Automatically load saved data on startup
    }

    public void addStudent(Student student) {
        students.add(student);
        saveData();
        System.out.println(">>> Student added successfully!");
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentByRegNo(String regNo) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getRegNo().equalsIgnoreCase(regNo)) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student with Reg No '" + regNo + "' not found.");
    }

    public boolean removeStudent(String regNo) throws StudentNotFoundException {
        Student student = findStudentByRegNo(regNo);
        students.remove(student);
        saveData();
        System.out.println(">>> Student removed successfully!");
        return true;
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading existing student data: " + e.getMessage());
        }
    }
}