package com.zlin.jvm.myClassLoader;

public class Student {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Object student) {
        this.student = (Student) student;
    }
}
