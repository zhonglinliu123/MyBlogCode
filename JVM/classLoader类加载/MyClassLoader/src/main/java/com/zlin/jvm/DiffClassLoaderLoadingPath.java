package com.zlin.jvm;

import java.util.Arrays;
import java.util.List;

public class DiffClassLoaderLoadingPath {

    public static void main(String[] args) {
//        bootClassLoaderLoadingPath();

//        extClassLoaderLoadingPath();

        appClassLoaderLoadingPath();
    }

    /**
     * 启动类加载器加载的目录
     */
    private static void bootClassLoaderLoadingPath () {
        // 获取启动类加载器加载的目录
        String bootStrapLoadingPath = System.getProperty("sun.boot.class.path");
        // 把加载的目录转为集合
        List<String> bootStrapLoadingPathList = Arrays.asList(bootStrapLoadingPath.split(";"));

        for (String bootPath : bootStrapLoadingPathList) {
            System.out.println("启动类加载器-----加载的目录：" + bootPath);
        }
    }

    /**
     * 扩展类加载器加载的目录
     */
    private static void extClassLoaderLoadingPath () {
        // 获取扩展类加载器加载的目录
        String extClassLoadingPath = System.getProperty("java.ext.dirs");
        // 把加载的目录转为集合
        List<String> extClassLoadingPathList = Arrays.asList(extClassLoadingPath.split(";"));

        for (String extPath : extClassLoadingPathList) {
            System.out.println("扩展类加载器-----加载的目录：" + extPath);
        }
    }

    /**
     * App类加载器加载的目录
     */
    private static void appClassLoaderLoadingPath () {
        // 获取App类加载器加载的目录
        String appClassLoadingPath = System.getProperty("java.class.path");
        // 把加载的目录转为集合
        List<String> appClassLoadingPathList = Arrays.asList(appClassLoadingPath.split(";"));

        for (String appPath : appClassLoadingPathList) {
            System.out.println("App类加载器-----加载的目录：" + appPath);
        }
    }
}
