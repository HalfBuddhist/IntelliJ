package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Qingwei on 16/6/28.
 */
public class RegExPatternsIntroDB {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0;i<n;i++){
            String name = sc.next();
            String mail = sc.next();
            if(mail.endsWith("@gmail.com")){
                names.add(name);
            }
        }

        //sort
        Collections.sort(names);

        //pirnt
        for (String ele : names){
            System.out.println(ele);
        }
    }
}
