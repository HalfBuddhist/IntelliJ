package com.acmerblog;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumIn2DMatrix {

    //idx in the subarray
    private static int max_value_idx_subarray_start;
    private static int max_value_idx_subarray_end;

    //idx for the 2d matrix
    private static int max_value_idx_submatrix_row_start;
    private static int max_value_idx_submatrix_row_end;
    private static int max_value_idx_submatrix_col_start;
    private static int max_value_idx_submatrix_col_end;

    /**
     * 1. 方法1：直接计算
     * O(m^2*n^2)
     */
    //遍历所有二维数组的矩形区域
    public static int maxArrSum(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int p[][] = arrSum(arr);
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int endi = i; endi <= m; endi++) {
                    for (int endj = j; endj <= n; endj++) {
                        int sum = p[endi][endj] - p[i - 1][endj] - p[endi][j - 1] + p[i - 1][j - 1];
                        if (ans < sum) {
                            ans = sum;
                            max_value_idx_submatrix_row_start = i - 1;
                            max_value_idx_submatrix_row_end = endi - 1;
                            max_value_idx_submatrix_col_start = j - 1;
                            max_value_idx_submatrix_col_end = endj - 1;
                        }
                    }
                }
            }
        }
        return ans;
    }

    //arrSum函数即做预处理，得到数组p，p[i][j]表示已Rect(0, 0, i-1, j-1)矩形区域的总和。
    // 有了p数组就可以直接计算出任意矩形的总和。
    public static int[][] arrSum(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int p[][] = new int[m + 1][n + 1];
        p[0][0] = arr[0][0];
        for (int i = 0; i <= m; i++) p[i][0] = 0;
        for (int i = 0; i <= n; i++) p[0][i] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                p[i][j] = p[i - 1][j] + p[i][j - 1] + arr[i - 1][j - 1] - p[i - 1][j - 1];
            }
        }
        return p;
    }

    /**
     * 2. 方法二 转化为一维数组计算
     * <p/>
     * 这里并不是把整个二维数组转化为一维的，而是说把部分连续的行合并，看成是一行计算。
     * 这样枚举所有连续的行，把这些连续的行看成是一个整体，把一列看成是一个数字，
     * 问题就转化为上面的一维数组的算法了。还可以采用上面的预处理方法，在O（1）的时间内计算出任意一列的和。
     * 代码如下，colSum函数即为预处理函数，我们还可以根据M，N的大小做些优化, 即对其进行转置，保证m<n。
     * 算法的复杂度为 O(N * M * min(M,N) ).
     */
    static int maxArrSum2(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        if (m > n) {
            //对数组进行逆置
            arr = reverseArr(arr);
            int tmp = m;
            m = n;
            n = tmp;
        }
        //ensure n>=m
        int p[][] = colSum(arr);
        int tempMax = Integer.MIN_VALUE;

        //h表示当前矩阵的高度. 即把多少行合并为一行看
        for (int h = 1; h <= m; h++) {
            for (int i = 1; i + h - 1 <= m; i++) {
                int endLine = i + h - 1;
                //转化为长度为n一维数组，复杂度为O(n)
                int it_max = maxSum(p, i, endLine, n);
                if (it_max > tempMax) {
                    max_value_idx_submatrix_row_start = i - 1;
                    max_value_idx_submatrix_row_end = endLine - 1;
                    max_value_idx_submatrix_col_start = max_value_idx_subarray_start;
                    max_value_idx_submatrix_col_end = max_value_idx_subarray_end;
                }
                tempMax = Math.max(tempMax, it_max);
            }
        }
        return tempMax;
    }


    /**
     * 求一维数组的最大连续和
     *
     * @param p
     * @param startLine
     * @param endLine
     * @param n
     * @return
     */
    static int maxSum(int p[][], int startLine, int endLine, int n) {
        int ans = Integer.MIN_VALUE;
        int start = 1, end = 1;
        int cmax = 0;//to culculate
        while (start <= n && end <= n) {
            cmax += p[endLine][end] - p[startLine - 1][end];
            if (cmax > ans) {
                max_value_idx_subarray_start = start - 1;
                max_value_idx_subarray_end = end - 1;
            }
            ans = Math.max(cmax, ans);
            if (cmax < 0) {
                start = end + 1;
                cmax = 0;
            }
            end++;
        }
        return ans;
    }

    //calculate the column sum
    static int[][] colSum(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int p[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                p[i][j] = p[i - 1][j] + arr[i - 1][j - 1];
        return p;
    }


    static int[][] reverseArr(int arr[][]) {
        int m = arr.length;
        int n = arr[0].length;
        int newArr[][] = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                newArr[j][i] = arr[i][j];
        return newArr;
    }

    public static void main(String[] args) {
        int arr[][] = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };

        int ans = maxArrSum(arr);
        System.out.println("method 1: " + ans);
        System.out.println("method 1: " + max_value_idx_submatrix_row_start + " " +
                max_value_idx_submatrix_row_end + " " +
                max_value_idx_submatrix_col_start + " " +
                max_value_idx_submatrix_col_end);
        ans = maxArrSum2(arr);
        System.out.println("method 2: " + ans);
        System.out.println("method 2: " + max_value_idx_submatrix_row_start + " " +
                max_value_idx_submatrix_row_end + " " +
                max_value_idx_submatrix_col_start + " " +
                max_value_idx_submatrix_col_end);
    }

}
