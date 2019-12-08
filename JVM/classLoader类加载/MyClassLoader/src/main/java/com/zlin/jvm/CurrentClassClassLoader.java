package com.zlin.jvm;


public class CurrentClassClassLoader {
    public static void main(String[] args) {
        // 得到当前类的类加载器 (AppClassLoader)
        System.out.println(CurrentClassClassLoader.class.getClassLoader());

        // ExtClassLoader
        System.out.println(CurrentClassClassLoader.class.getClassLoader().getParent());

        // BootStrapClassLoader   null就表示是BootStrapClassLoader
        System.out.println(CurrentClassClassLoader.class.getClassLoader().getParent().getParent());
    }
}
