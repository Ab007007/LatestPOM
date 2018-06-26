package com.qst.ohrm.test;

import java.io.File;

public class TestMD {

	public static void main(String a[]){
        File file = new File("reports/ss/");
        System.out.println(file.getAbsolutePath());
        File[] fileList = file.listFiles();
        for(File name:fileList){
            System.out.println(name.getAbsoluteFile());
        }
    }
}
