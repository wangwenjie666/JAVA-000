package com.vijayian.hello;

import java.io.*;

/**
 * hello
 *
 * @author ViJay
 * @date 2021-02-06
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        new HelloClassLoader().findClass("com.vijayian.hello.Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "D:/opt/JAVA-000/Week_01/code1/src/";
        String suffix = name.replace(".", "/");
        String filePath = path + suffix + ".class";
        System.out.println(filePath);
        File file = new File(filePath);
        int length = (int) file.length();
        ByteArrayOutputStream out = new ByteArrayOutputStream(length);
        FileInputStream in = null;
        byte[] buffer = new byte[1024];
        try {
            in = new FileInputStream(file);
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        buffer = out.toByteArray();

        return defineClass(name, buffer, 0, buffer.length);
    }
}
