package com.autuo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteStringDemo {
    
    public static void main(String[] args) {
        //out(new File("D:\\下载\\autuo.txt"));
        in(new File("D:\\下载\\autuo.txt"));
    }
    @SuppressWarnings("unused")
	private static void out(File f) {
        try {
            OutputStream out = new FileOutputStream(f);
            String info = "枯藤老树昏鸦，小桥流水人家";
            out.write(info.getBytes());
            out.close();
            System.out.println("文件写入成功");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void in(File f) {
        try {
            InputStream in = new FileInputStream(f);
            byte[] bytes = new byte[30];
            StringBuilder buf = new StringBuilder();
			int len = -1;
            while ((len = in.read(bytes)) != -1) {
                buf.append(new String(bytes, 0, len));
            }
            System.out.println(buf);
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}