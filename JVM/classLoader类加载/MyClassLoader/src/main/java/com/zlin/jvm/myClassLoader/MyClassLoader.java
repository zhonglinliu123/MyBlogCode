package com.zlin.jvm.myClassLoader;

import java.io.*;

/**
 * 自定义的类加载器
 */
public class MyClassLoader extends ClassLoader{
    private final static String fileSuffixExt = ".class";

    // 加载器的名称
    private String classLoaderName;

    // 加载器的加载路径
    private String loadPath;

    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        // 指定当前类加载器的父类加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    public MyClassLoader(String classLoaderName) {
        // 不指定父类加载器，则默认使用AppClassLoader加载器作为父类加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    /**
     * 方法实现说明
     * @param name: 类的二进制名称
     */
    private byte[] loadClassData(String name) {
        byte[] data = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;

        try {
            name = name.replace(".", "\\");
            String fileName = loadPath + name + fileSuffixExt;
            File file = new File(fileName);
            inputStream = new FileInputStream(file);

            byteArrayOutputStream = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = inputStream.read())) {
                byteArrayOutputStream.write(ch);
            }
            data = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    protected Class<?> findClass(String name) {
        byte[] data = loadClassData(name);
        System.out.println("MyClassLoader加载的类:" + name);
        return defineClass(name, data, 0, data.length);
    }
}
