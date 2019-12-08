package com.zlin.jvm.fulldelegation;

public class Test {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        System.out.println("Teacher类的ClassLoader:" + teacher.getClass().getClassLoader());
    }
}
