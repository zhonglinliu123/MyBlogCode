package com.zlin.jvm.myClassLoader;

import java.lang.reflect.Method;

public class DifferentClassLoaderNameSpaceTest {
    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader1 = new MyClassLoader("myClassLoader1");
        myClassLoader1.setLoadPath("E:\\zlin\\");

        MyClassLoader myClassLoader2 = new MyClassLoader("myClassLoader2");
        myClassLoader2.setLoadPath("E:\\zlin\\");

        // 通过myClassLoader1加载Student
        Class<?> clazz1 = myClassLoader1.loadClass("com.zlin.jvm.myClassLoader.Student");
        System.out.println("class1的类加载器" + clazz1.getClassLoader());

        // 通过myClassLoader2加载Student
        Class<?> clazz2 = myClassLoader2.loadClass("com.zlin.jvm.myClassLoader.Student");
        System.out.println("class2的类加载器" + clazz2.getClassLoader());

        System.out.println("class1 == class2 :" + (clazz1 == clazz2));

        // 当classpath目录下Student.class存在时，clazz1和clazz2都是AppClassLoader来进行加载
        // 当把classpath目录下的Student.class删除后，clazz1使用myClassLoader1进行加载，class2使用myClassLoader2进行加载
        // 两个类的命名空间不同
        // java.lang.ClassCastException: com.zlin.jvm.myClassLoader.Student cannot be cast to com.zlin.jvm.myClassLoader.Student

        // 模拟问题
        Object student1 = clazz1.newInstance();

        Object student2 = clazz2.newInstance();


        Method method = clazz1.getMethod("setStudent", Object.class);

        method.invoke(student1, student2);

    }
}
