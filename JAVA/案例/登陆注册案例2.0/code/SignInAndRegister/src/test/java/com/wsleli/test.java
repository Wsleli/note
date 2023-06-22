package com.wsleli;

import com.wsleli.util.CheckCodeUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class test {
    public static void main(String[] args) throws IOException {
        //生成验证码的图片位置
        OutputStream fos = new FileOutputStream("d://a.jpg");
        //checkCode为最终验证码的数据
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, fos, 4);
        System.out.println(checkCode);
    }
}
