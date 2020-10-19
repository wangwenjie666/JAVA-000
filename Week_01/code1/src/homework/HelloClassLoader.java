package homework;

import java.io.*;

/**
 * classloader
 *
 * @author wangwenjie
 * @date 2020-10-17
 */
public class HelloClassLoader extends ClassLoader {
    //方式1
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = initClassFile();
        return defineClass(name, bytes, 0, bytes.length);
    }


    //生成类文件
    public byte[] initClassFile() {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(".\\src\\homework\\Hello.xlass"));
            out = new ByteArrayOutputStream(1024);
            int _byte = 0;
            byte[] temp = new byte[1024];
            while ((_byte = in.read(temp)) != -1) {
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = (byte) (255 - temp[i]);
                }
                out.write(temp, 0, _byte);
            }
        } catch (Exception e) {
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
        if (out != null) {
            return out.toByteArray();
        }
        throw new RuntimeException("生成class文件异常");
    }

    private void generateClassFile() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(".\\src\\homework\\Hello.xlass");
            out = new FileOutputStream(".\\src\\Hello.class");
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = in.read(bytes)) != -1) {
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (255 - bytes[i]);
                }
                out.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}