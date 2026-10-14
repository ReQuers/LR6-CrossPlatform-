package org.example;

import dao.StudentDao;
import model.Student;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Main {

    static void main() throws IOException {
        StudentDao studentDao = new StudentDao();

        studentDao.createNewTable();
//        System.out.println("\n--- 0. ДОДАВАННЯ ТЕСТОВИХ ДАНИХ ---");
//        List<Student> sampleStudents = List.of(
//                new Student("Андрій", "Шевченко", 22, "sheva@example.com"),
//                new Student("Марія", "Коваль", 19, "koval@example.com"),
//                new Student("Іван", "Петренко", 21, "petrenko@example.com"),
//                new Student("Олена", "Мельник", 20, "melnyk@example.com"),
//                new Student("Максим", "Бойко", 23, "boyko@example.com")
//        );
//
//        // Записуємо кожного студента в базу
//        for (Student s : sampleStudents) {
//            studentDao.createStudent(s);
//        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=====================================");
            System.out.println("1. Переглянути всіх студентів (READ)");
            System.out.println("2. Додати нового студента (CREATE)");
            System.out.println("3. Оновити дані студента (UPDATE)");
            System.out.println("4. Видалити студента (DELETE)");
            System.out.println("0. Вихід з програми");
            System.out.println("=====================================");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- СПИСОК УСІХ СТУДЕНТІВ ---");
                    List<Student> students = studentDao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("База даних наразі порожня.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- ДОДАВАННЯ НОВОГО СТУДЕНТА ---");
                    System.out.print("Введи ім'я: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Введи прізвище: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Введи вік: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введи email: ");
                    String email = scanner.nextLine();

                    Student newStudent = new Student(firstName, lastName, age, email);
                    studentDao.createStudent(newStudent);
                    break;

                case 3:
                    System.out.println("\n--- ОНОВЛЕННЯ ДАНИХ СТУДЕНТА ---");
                    System.out.print("Введи ID студента: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введи нове ім'я: ");
                    String newFirstName = scanner.nextLine();

                    System.out.print("Введи нове прізвище: ");
                    String newLastName = scanner.nextLine();

                    System.out.print("Введи новий вік: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введи новий email: ");
                    String newEmail = scanner.nextLine();

                    Student updatedStudent = new Student(updateId, newFirstName, newLastName, newAge, newEmail);
                    studentDao.updateStudent(updatedStudent);
                    break;

                case 4:
                    System.out.println("\n--- ВИДАЛЕННЯ СТУДЕНТА ---");
                    System.out.print("Введи ID студента: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    studentDao.deleteStudent(deleteId);
                    break;

                case 0:
                    System.out.println("Вихід з програми.");
                    running = false;
                    break;

                default:
                    System.out.println("Error");
            }
        }

    }

}
