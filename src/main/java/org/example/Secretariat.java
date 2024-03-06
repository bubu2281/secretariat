package org.example;

import java.io.*;
import java.util.*;

public class Secretariat {
    private static List<Student> students = new ArrayList<>();
    private static Map<String, Course<?>> courses = new TreeMap<>(new ComparatorCourseAlphabetically());
    public static void addStudent(Student student) throws DuplicatedStudent {
        for (Student student1 : students) {
            if (student1.getName().equals(student.getName())) {
                throw new DuplicatedStudent(student);
            }
        }
        students.add(student);
    }
    public static void resetSecretariat() {
        students = new ArrayList<>();
        courses = new TreeMap<>(new ComparatorCourseAlphabetically());
    }
    public static void readGrades(String currentDirectory) {
        String prefix = currentDirectory + "/" + "note_";
        for(int i = 1; i <= 10; i++) {
            File gradesFile = new File(prefix + i + ".txt");
            try {
                Scanner s = new Scanner(gradesFile);
                while(s.hasNextLine()) {
                    String[] grades = s.nextLine().split(" -");
                    for (Student student : students) {
                        if (student.getName().equals(grades[0])) {
                            student.setGrade(Double.parseDouble(grades[1]));
                        }
                    }
                }
            } catch (FileNotFoundException ignored) {
            }
        }
    }
    public static void postGrades(File outputFile) throws IOException {
        sortStudents();
        FileWriter fw = new FileWriter(outputFile, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println("***");
        for (Student student : students) {
            pw.println(student.getName() + " - " + student.getGrade());
        }
        pw.close();
    }
    public static void sortStudents() {
        Collections.sort(students);
    }
    public static void contestation(String name, String grade) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                student.setGrade(Double.parseDouble(grade));
                break;
            }
        }
    }
    public static void addCourse(String studyProgram, String courseName, String maximumCapacity) {
        if (studyProgram.equals("licenta")) {
            courses.put(courseName, new Course<BachelorStudent>(courseName, Integer.parseInt(maximumCapacity)));
        }
        if (studyProgram.equals("master")) {
            courses.put(courseName ,new Course<MasterStudent>(courseName, Integer.parseInt(maximumCapacity)));
        }
    }

    public static void addPreferences(String studentName, String[] preferences) {
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                for (int i = 2; i < preferences.length; i++) {
                    student.addPreference(preferences[i]);
                }
                break;
            }
        }
    }
    public static void assignStudents() {
        sortStudents();
        for(Student student : students) {
            for(String preference : student.getPreferences()) {
                Course<?> course = courses.get(preference);
                Collections.sort(course.getStudentList());
                if (course.getMaximumCapacity() > course.getStudentList().size()
                       || course.getStudentList().get(course.getStudentList().size() - 1).getGrade() == student.getGrade()
                ) {
                    course.assignStudent(student);
                    break;
                }
            }
        }
        for(Student student : students) {
            if (student.getCourse() != null) {
                continue;
            }
            for (String course : courses.keySet()) {
                if (courses.get(course).getMaximumCapacity() > courses.get(course).getStudentList().size()) {
                    courses.get(course).assignStudent(student);
                    break;
                }
            }
        }
    }
    public static void postCourse(String courseName, File outputFile) throws IOException {
        courses.get(courseName).displayCourseTo(outputFile);
    }

    public static void postStudent(String studentName, File outputFile) throws IOException {
        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                student.displayStudentTo(outputFile);
                break;
            }
        }
    }


}
