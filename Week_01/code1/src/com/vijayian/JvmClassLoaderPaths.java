package com.vijayian;

import sun.misc.Launcher;

import java.net.URL;

/**
 * 类加载器加载了哪些jar
 *
 * @author ViJay
 * @date 2021-02-05
 */
public class JvmClassLoaderPaths {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器 BootstrapClassLoader");
        for (URL urL : urLs) {
            System.out.println("==>" + urL.toExternalForm());
        }

        ClassLoader classLoader = JvmClassLoaderPaths.class.getClassLoader();
        System.out.println(classLoader);
    }
}
