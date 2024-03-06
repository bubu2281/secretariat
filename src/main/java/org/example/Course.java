package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Course<T extends Student> {
    private String name;
    private final int maximumCapacity;
    private ArrayList<T> studentList;
    public Course(String name, int maximumCapacity) {
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.studentList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public ArrayList<T> getStudentList() {
        return studentList;
    }

    public void assignStudent(Student student) {
        student.setCourse(this.getName());
        this.studentList.add((T)student);
    }

    public void displayCourseTo(File outputFile) throws IOException {
        FileWriter fw = new FileWriter(outputFile, true);
        PrintWriter pw = new PrintWriter(fw);
        this.getStudentList().sort(new ComparatorStudentsAlphabetically());
        pw.println("***");
        pw.print(this.getName());
        pw.println(" (" + this.getMaximumCapacity() + ")");
        for (Student student : this.getStudentList()) {
            pw.println(student.getName() + " - " + student.getGrade());
        }
        fw.close();
        pw.close();
    }

}
