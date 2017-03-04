package com.hackerrank.week_code_21;


import com.lqw.common.WebPath;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 枚举每一个可能的bc, 将其余的点按照以bc为x轴坐标转换后的夹角顺序，并找到所有合法的a与d相乘即可
 * O(n^3logn)
 */
public class TheLetterN {

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        Point(PointAngle pa) {
            this.x = pa.distance * Math.cos(pa.angle);
            this.y = pa.distance * Math.sin(pa.angle);
        }
    }

    static class PointAngle {
        double angle;
        double distance;

        PointAngle(double angle, double distance) {
            this.angle = angle; //hu du
            this.distance = distance;
        }

        PointAngle(Point p) {
            distance = Math.sqrt(p.x * p.x + p.y * p.y);
            if (new Double(distance).equals(new Double("0"))) {
                angle = 0;
            } else {
                double hudu = Math.asin(p.y / distance);
                if (p.x < 0) {
                    this.angle = Math.PI - hudu;
                } else {
                    this.angle = hudu >= 0 ? hudu : 2 * Math.PI + hudu;
                }
            }
        }
    }

    public static double[] transfer_as_point(Point start, Point end, Point[] points) {
        double[] angles = new double[points.length];
        int i = 0;
        for (Point point : points) {
            //end
            PointAngle end_shift_pa = new PointAngle(new Point(end.x - start.x, end.y - start.y));
            Point end_shift_rot_p = new Point(end_shift_pa.distance, 0);
            //point
            PointAngle point_shift_pa = new PointAngle(new Point(point.x - start.x, point.y - start.y));
            PointAngle point_shift_rot_pa = new PointAngle(point_shift_pa.angle >= end_shift_pa.angle ?
                    point_shift_pa.angle - end_shift_pa.angle : 2 * Math.PI + point_shift_pa.angle - end_shift_pa.angle,
                    point_shift_pa.distance);
            Point point_shift_rot_p = new Point(point_shift_rot_pa);
            Point point_shift_rot_shift_p = new Point(point_shift_rot_p.x - end_shift_rot_p.x, point_shift_rot_p.y - end_shift_rot_p.y);
            PointAngle point_shift_rot_shift_pa = new PointAngle(point_shift_rot_shift_p);

            if (point_shift_rot_shift_pa.angle <= Math.PI) {
                angles[i++] = point_shift_rot_shift_pa.angle;
            } else {
                angles[i++] = point_shift_rot_pa.angle;
            }
        }
        return angles;
    }

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/week_code_21/input.txt").getPath()));
        //Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(sc.nextInt(), sc.nextInt());
        }

        //resolve
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //transfer to the angle and sort
                double[] arrays_angle = transfer_as_point(points[i], points[j], points);
                Arrays.sort(arrays_angle);

                //find the up and downs binary search
                int index_pi = Arrays.binarySearch(arrays_angle, Math.PI);
                if (index_pi < 0) index_pi = Math.abs(index_pi) - 1;
                if (index_pi == 0 || index_pi == arrays_angle.length) continue;

                int idx_pi2 = Arrays.binarySearch(arrays_angle, 0, index_pi, Math.PI / 2);
                if (idx_pi2 < 0) idx_pi2 = Math.abs(idx_pi2) - 1;
                if (idx_pi2 == index_pi) continue;
                int start_cnt = idx_pi2;
                while (start_cnt >=0 &&  arrays_angle[start_cnt] >= Math.PI / 2) {
                    start_cnt--;
                }
                int end_cnt = index_pi - 1;
                while (end_cnt >=0 && arrays_angle[end_cnt] >= Math.PI) {
                    end_cnt--;
                }
                int m1 = end_cnt - start_cnt;
                m1 = m1 >= 0 ? m1 : 0;

                int idx_pi32 = Arrays.binarySearch(arrays_angle, index_pi, arrays_angle.length, 3 * Math.PI / 2);
                if (idx_pi32 < 0) idx_pi32 = Math.abs(idx_pi32) - 1;
                if (idx_pi32 == arrays_angle.length) continue;
                start_cnt = idx_pi32;
                while (start_cnt >=0 && arrays_angle[start_cnt] >= Math.PI * 3 / 2) {
                    start_cnt--;
                }
                end_cnt = arrays_angle.length - 1;
                int m2 = end_cnt - start_cnt;
                m2 = m2 >= 0 ? m2 : 0;

                //calc
                total += m1 * m2;
            }
        }

        //output
        System.out.println(total);

        System.out.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
