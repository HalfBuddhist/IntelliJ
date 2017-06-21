package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueensII_52 {
    private int nQueens_recursive(int n, int cur_row, int[] locations_in_row,
                                  int[] constrains_col, int[] constrains_slash, int[] constrains_backslash) {
        //end condition
        if (cur_row == n) {
            return 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {//every col
            if (constrains_col[i] == 0 && constrains_slash[cur_row + i] == 0 && constrains_backslash[cur_row + (n - 1 - i)] == 0) {
                //put int in this location
                locations_in_row[cur_row] = i;
                //add new constrains
                constrains_col[i]++;
                constrains_slash[cur_row + i]++;
                constrains_backslash[cur_row + (n - 1 - i)]++;
                //search under this constrain.
                ans += nQueens_recursive(n, cur_row + 1, locations_in_row, constrains_col, constrains_slash, constrains_backslash);
                //restore the state, go to the next location
                locations_in_row[cur_row] = 0;
                constrains_col[i]--;
                constrains_slash[cur_row + i]--;
                constrains_backslash[cur_row + (n - 1 - i)]--;
            }
        }
        return ans;
    }

    /**
     * Backtracking, base algo
     * SPCS, dfs in tree or graph or solution space.
     * <p/>
     * try to put the queen to every possible place under the constrains row by row.
     * when put a queen, update the column attack info, so does the slack and backslash.
     * constrains_col indicate if this column can be attacked by a queen, so does the the slack and backslash.
     * length of the column is n, for (i,j) the target column is (j)
     * length of the slash  is 2n-1, for (i,j) the target slash  is (i+j)
     * length of the backslash is 2n-1, for (i,j) the target column is (i+(n-1-j))
     * For both above coordinates, index start from 0, and n is the nubmer of queens, so is the number of rows and columns.
     * <p/>
     * for n<=0 no solutions provided, for 1=n, one solution, for n=2,3 no solution, for n=4, 2 sol;
     * <p/>
     * O(n^n)
     * AC
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int ans = 0;
        if (n <= 0) return ans;
        int locations_in_row[] = new int[n];
        int constrains_col[] = new int[n];
        int constrains_slash[] = new int[2 * n - 1];
        int constrains_backslash[] = new int[2 * n - 1];
        ans = nQueens_recursive(n, 0, locations_in_row, constrains_col, constrains_slash, constrains_backslash);
        return ans;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        //resolve
        int ans = (new NQueensII_52()).totalNQueens(5);
        System.out.println(ans);

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
