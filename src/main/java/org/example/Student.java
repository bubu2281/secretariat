package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

public class Student implements Comparable<Student> {
    private double grade;
    private final String name;
    private final LinkedHashSet<String> preferences;
    private String course = null;
    public Student(String name) {
        this.name = name;
        this.grade = 0;
        this.preferences = new LinkedHashSet<>();
    }

    public double getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<String> getPreferences() {
        return preferences;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public int compareTo(Student o) {
        if (this.getGrade() == o.getGrade()) {
            return this.getName().compareTo(o.getName());
        } else {
            return Double.compare(o.getGrade(), this.getGrade());
        }
    }

    public boolean equals(Student student) {
        return this.getName().equals(student.getName());
    }

    public void addPreference(String preference) {
        this.preferences.add(preference);
    }
    public void displayStudentTo(File outputFile) throws IOException {
        FileWriter fw = new FileWriter(outputFile, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("***");
        if (this instanceof BachelorStudent) {
            pw.print("Student Licenta: ");
        }
        if (this instanceof MasterStudent) {
            pw.print("Student Master: ");
        }
        pw.print(this.getName() + " - " + this.getGrade() + " - ");
        pw.println(this.getCourse());
        fw.close();
        pw.close();
    }
}
