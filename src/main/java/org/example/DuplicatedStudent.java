package org.example;

public class DuplicatedStudent extends Exception {
    DuplicatedStudent(Student student) {
        super("Student duplicat: " + student.getName());
    }
}
