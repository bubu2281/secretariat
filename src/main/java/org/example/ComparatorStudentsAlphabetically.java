package org.example;

import java.util.Comparator;

public class ComparatorStudentsAlphabetically implements Comparator<Student> {

    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
