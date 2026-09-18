package com.studentapp;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("==================================================");
        System.out.println("     STUDENT MANAGEMENT & GRADE TRACKING SYSTEM   ");
        System.out.println("==================================================");

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Reg No");
            System.out.println("4. Add Course Grade for Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String choiceStr = scanner.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println(">>> Invalid input! Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Registration Number: ");
                    String regNo = scanner.nextLine().trim();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine().trim();

                    Student newStudent = new Student(name, regNo, dept);
                    manager.addStudent(newStudent);
                    break;

                case 2:
                    List<Student> allStudents = manager.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println(">>> No student records found.");
                    } else {
                        System.out.println("\n--- Registered Students ---");
                        for (Student s : allStudents) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Registration Number to Search: ");
                    String searchReg = scanner.nextLine().trim();
                    try {
                        Student found = manager.findStudentByRegNo(searchReg);
                        System.out.println("\n>>> Student Found:");
                        System.out.println(found);
                    } catch (StudentNotFoundException e) {
                        System.out.println(">>> " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Registration Number: ");
                    String gradeReg = scanner.nextLine().trim();
                    try {
                        Student student = manager.findStudentByRegNo(gradeReg);
                        System.out.print("Enter Course Name: ");
                        String course = scanner.nextLine().trim();
                        System.out.print("Enter Grade (0.0 to 10.0): ");
                        double grade = Double.parseDouble(scanner.nextLine().trim());

                        student.addGrade(course, grade);
                        manager.saveData();
                        System.out.println(">>> Grade added successfully!");
                    } catch (StudentNotFoundException e) {
                        System.out.println(">>> " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println(">>> Invalid grade entered! Please enter a valid decimal number.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Registration Number to Remove: ");
                    String removeReg = scanner.nextLine().trim();
                    try {
                        manager.removeStudent(removeReg);
                    } catch (StudentNotFoundException e) {
                        System.out.println(">>> " + e.getMessage());
                    }
                    break;

                case 6:
                    exit = true;
                    System.out.println(">>> Exiting application. All data saved. Goodbye!");
                    break;

                default:
                    System.out.println(">>> Invalid option! Please select between 1 and 6.");
            }
        }
        scanner.close();
    }
}