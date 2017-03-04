package com.java.scanner;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scanner next[Int,Shor, Float]()每次都试图从输入源读取下一个delimiter之前的元素，
 * nextLine则试图读取一行，返回值并没有回车
 * delimite默认为"    \t\n\r   ", 正则式应该为r"[\t|\r|\n| ]+"
 */
public class TestScanner {

    public static void main(String[] args) {
        Scanner scanner;
        try {
            //scanner from the file
            System.out.println(WebPath.getAbsolutePathWithClass("/com/java/scanner/input.txt").toString());
            System.out.println(TestScanner.class.getResource("/com/java/scanner/input.txt").getPath());
            scanner = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/java/scanner/input.txt").getPath()));
//            scanner = new Scanner(new File("/Users/Qingwei/Documents/workspace/IntelliJ/SyntaxOfJava/out/production/SyntaxOfJava/com/java/scanner/input.txt"));

            //scanner from the stand input device.
            //        Scanner scanner = new Scanner(new BufferedInputStream(System.in));

            //test the delimiter
            Pattern pattern = scanner.delimiter();
            Matcher matcher = pattern.matcher("    \t\n\r   ");
            System.out.println(matcher.matches());
//        System.out.println(scanner.delimiter());

            //next method
            System.out.print(scanner.next());
            System.out.println(scanner.nextLine());
//            while (scanner.hasNextLine()) {
//                System.out.print(scanner.nextLine());
//            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("Hello world!");
    }
}