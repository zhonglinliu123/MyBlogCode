package com.zlin.jvm.myClassLoader;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException{
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        // 自定义加载器myClassLoader的加载路径
        myClassLoader.setLoadPath("E:\\zlin\\");
        // 加载Student类
        Class<?> clazz = myClassLoader.loadClass("com.zlin.jvm.myClassLoader.Student");
        System.out.println(clazz.getClassLoader());

        // E:\zlin\路径下存在Student.class,  AppClassLoader的加载路径下也存在
        // 根据双亲委托模型，会使用AppClassLoader来加载Student类
        // 当把classpath下的Student.class文件删掉后，
        // AppClassLoader的加载路径下找不到Student.class  才会使用MyClassLoader来进行加载
    }
}
