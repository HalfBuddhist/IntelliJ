package com.acmerblog;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumIn2DCycleMatrix {

    //idx in the subarray, may be larger than the legal value,
    // mod them with the len when output use the idx.
    private static int max_value_idx_subarray_start;
    private static int max_value_idx_subarray_end;

    //idx for the 2d matrix, may be larger than the legal value,
    // mod them with the len when output use the idx.
    private static int max_value_idx_submatrix_row_start;
    private static int max_value_idx_submatrix_row_end;
    private static int max_value_idx_submatrix_col_start;
    private static int max_value_idx_submatrix_col_end;


    /**
     * 全向首尾相连
     * 转化为一维数组计算
     *
     * @param arr
     * @return
     */
    public static int maxMatrixSumWithFulLCycle(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        //对数组进行逆置，以减小时间复杂度， //ensure n>=m
        if (m > n) {
            arr = reverseArr(arr);
            int tmp = m;
            m = n;
            n = tmp;
        }

        //计算列合
        int p[][] = colSum(arr);
        int tempMax = Integer.MIN_VALUE;

        //h表示当前矩阵的高度. 即把多少行合并为一行看
        for (int h = 1; h <= m; h++) {
            for (int i = 0; i < m; i++) {
                int endLine = i + h - 1;
                //转化为长度为n一维数组，复杂度为O(n)
                int max_it = maxSum(p, i, endLine, n);
                if (max_it > tempMax) {
                    max_value_idx_submatrix_row_start = i;
                    max_value_idx_submatrix_row_end = endLine;
                    max_value_idx_submatrix_col_start = max_value_idx_subarray_start;
                    max_value_idx_submatrix_col_end = max_value_idx_subarray_end;
                }
                tempMax = Math.max(tempMax, max_it);
            }
        }
        return tempMax;
    }

    /**
     * 只有横向的是首尾相连的
     * 转化为一维数组计算
     */
    public static int maxMatrixSumWithRowCycle(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        //对数组进行逆置，以减小时间复杂度， //ensure n>=m
        if (m > n) {
            arr = reverseArr(arr);
            int tmp = m;
            m = n;
            n = tmp;
        }

        //计算列合
        int p[][] = colSum(arr);
        int tempMax = Integer.MIN_VALUE;

        //h表示当前矩阵的高度. 即把多少行合并为一行看
        for (int h = 1; h <= m; h++) {
            for (int i = 0; i + h - 1 < m; i++) {
                int endLine = i + h - 1;
                //转化为长度为n一维数组，复杂度为O(n)
                int it_max = maxSum(p, i, endLine, n);
                if (it_max > tempMax) {
                    max_value_idx_submatrix_row_start = i;
                    max_value_idx_submatrix_row_end = endLine;
                    max_value_idx_submatrix_col_start = max_value_idx_subarray_start;
                    max_value_idx_submatrix_col_end = max_value_idx_subarray_end;
                }
                tempMax = Math.max(tempMax, it_max);
            }
        }
        return tempMax;
    }


    /**
     * 求一维连续数组的最大连续和
     *
     * @param p
     * @param startLine
     * @param endLine
     * @param n
     * @return
     */
    static int maxSum(int arr[][], int startLine, int endLine, int n) {
        int m = arr.length;
        int start = 0, end = 0;
        int max_sum = Integer.MIN_VALUE;
        int t_sum = 0;
        while (start < n && end < n * 2 - 1) {
            if (end >= n && start == (end % n)) {//has a cycle, move start
                //move one more step to let in from the other side.
                t_sum -= (endLine < m ? arr[endLine][start] : (arr[endLine - m][start] + arr[m - 1][start])) -
                        (startLine > 0 ? arr[startLine - 1][start] : 0);
                start++;
                //ensure sum(start -> end-1) is positive
                int t = 0;
                int end_t = start;
                while (start < n && end_t < end) {
                    t += (endLine < m ? arr[endLine][end_t % n] : (arr[endLine - m][end_t % n] + arr[m - 1][end_t % n]))
                            - (startLine > 0 ? arr[startLine - 1][end_t % n] : 0);
                    if (t < 0) {
                        t_sum -= t;
                        start = end_t + 1;
                        t = 0;
                    }
                    end_t++;
                }
                if (start >= n) break;//finished, avoid duplicate search
            }

            //accumulate
            t_sum += (endLine < m ? arr[endLine][end % n] : (arr[endLine - m][end % n] + arr[m - 1][end % n]))
                    - (startLine > 0 ? arr[startLine - 1][end % n] : 0);
            if (t_sum > max_sum) { //could remember the index here
                max_value_idx_subarray_start = start;
                max_value_idx_subarray_end = end;
            }
            max_sum = Math.max(max_sum, t_sum);
            if (t_sum < 0) {
                t_sum = 0;
                start = end + 1;
            }
            end++;
        }
        return max_sum;
    }

    //calculate the column sum
    static int[][] colSum(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int p[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (i == 0) {
                    p[i][j] = arr[i][j];
                } else {
                    p[i][j] = p[i - 1][j] + arr[i][j];
                }
        return p;
    }

    /**
     * Matrix rotation
     *
     * @param arr
     * @return
     */
    static int[][] reverseArr(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int newArr[][] = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                newArr[j][i] = arr[i][j];
        return newArr;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int times = sc.nextInt();
        while (times-- > 0) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int arr[][] = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            System.out.println(MaxSumIn2DCycleMatrix.maxMatrixSumWithFulLCycle(arr));
            System.out.println((max_value_idx_submatrix_row_start + 1) + "\t" +
                    (max_value_idx_submatrix_row_end + 1) + "\t" +
                    (max_value_idx_submatrix_col_start + 1) + "\t" +
                    (max_value_idx_submatrix_col_end + 1) + "\t");
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}


/* test example.
9
2 3
1 -2 3
2 -1 3
4 4
1 -1 2 2
1 -3 2 1
-1 2 3 4
0 1 -2 2
3 3
-1 2 3
-2 2 4
1 2 3
4 4
-66 1 -23 59
65 -44 -26 -90
-41 -35 -3 54
83 -15 96 -25
5 5
-66 1 -23 59 65
-44 -26 -90 -41 -35
-3 54 83 -15 96
-25 -37 -11 -98 -93
-10 34 -63 -10 -67
5 5
1 2 3 4 5
1 2 3 3 5
1 2 4 5 3
2 3 4 2 4
4 4 6 7 3
4 4
-9 17 -16 -50
19 -26 28 8
12 14 -45 -5
31 -23 11 41
5 5
-1 -2 -3 -4 -5
2 -3 4 5 1
3 -4 5 1 2
4 -5 1 2 3
-5 -1 -2 -3 -4
3 3
1 2 3
-1 -2 -3
1 2 3
* the last to show the diff between the two methods
 */