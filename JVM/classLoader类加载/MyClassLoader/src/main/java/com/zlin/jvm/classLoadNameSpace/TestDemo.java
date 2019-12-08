package com.zlin.jvm.classLoadNameSpace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //  Class.forName("com.mysql.jdbc.Driver"); 会注册一个驱动com.mysql.jdbc.Driver

        // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
        // DriverManager的构造方法中的loadInitialDrivers()会通过SPI服务把mysql-connector-java-8.0.18.jar下META-INF/services/java.sql.Driver文件中的所有驱动都注册

        // 当把当前线程上下文的ClassLoader改为ExtClassLoader后，
        // 1.Class.forName("com.mysql.jdbc.Driver");任能注册一个驱动com.mysql.jdbc.Driver (反射  ==> 会触发类的初始化 ==> 执行静态代码块)
        //   在Driver的静态代码块中: DriverManager.registerDriver(new Driver());
        // 2.但是Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
        //   不能加载mysql-connector-java-8.0.18.jar下META-INF/services/java.sql.Driver文件中的类
        //   因为ExtClassLoader的加载路径不包含classpath/第三方jar, classpath/引入的第三方jar属于AppClassLoader的加载路径
        Thread.currentThread().setContextClassLoader(TestDemo.class.getClassLoader().getParent());



        // 反射  ==> 会触发类的初始化 ==> 执行静态代码块
        // Driver的初始化 ==>在静态代码块中使用了DriverManager ==> 触发DriverManager类的初始化 ==> loadInitialDrivers()方法
        // Driver.class在classpath下, ExtClassLoader不能加载Driver.class，
        // 不能加载mysql-connector-java-8.0.18.jar下META-INF/services/java.sql.Driver文件中的类
        // ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
        Class.forName("com.mysql.jdbc.Driver");

        // 如果当前线程上下文的ClassLoader是AppClassLoader,则不用Class.forName("com.mysql.jdbc.Driver");
        // 直接使用DriverManager.getConnection("","",""); 也能注册驱动
        // 通过Iterator遍历将mysql-connector-java-8.0.18.jar下META-INF/services/java.sql.Driver文件中的类 来进行Class.forName("");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");
    }
}
