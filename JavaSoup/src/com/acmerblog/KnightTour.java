package com.acmerblog;

/**
 * Java program for Knight Tour problem using backtracking.
 * <p>
 * 问题描述：在一个N*N 格子的棋盘上，有一只国际象棋的骑士在棋盘的左下角，骑士只能根据象棋的规则进行移动，
 * 要么横向跳动一格纵向跳动两格，要么纵向跳动一格横向跳动两格。骑士从第一个格子出发，每个格子只能访问一次，
 * 能否访问完所有的格子， 请找到一个解决方案。
 * 骑士也就是类似中国象棋中的马的走法。这个问题和典型的N皇后问题的比较类似的，都可以通过回溯法来解决。
 * <p>
 * Created by Qingwei on 2017/5/26.
 */
public class KnightTour {
    static int N = 8; // bourd size.

    /**
     * A utility function to check if i,j are valid indexes for N*N chessboard
     *
     * @param x
     * @param y
     * @param sol
     * @return
     */
    private static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    /* A utility function to print solution
       matrix sol[N][N] */
    private static void printSolution(int sol[][]) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.printf("%2d ", sol[N - 1 - x][y]);
            System.out.println();
        }
    }

    /* This function solves the Knight Tour problem
       using Backtracking.  This  function mainly
       uses solveKTUtil() to solve the problem. It
       returns false if no complete tour is possible,
       otherwise return true and prints the tour.
       Please note that there may be more than one
       solutions, this function prints one of the
       feasible solutions.  */
    public static boolean solveKT() {
        int sol[][] = new int[N][N];

        /* Initialization of solution matrix */
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

       /* xMove[] and yMove[] define next move of Knight.
          xMove[] is for next value of x coordinate
          yMove[] is for next value of y coordinate */
        int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Since the Knight is initially at the first block
        sol[0][0] = 0;

        /* Start from 0,0 and explore all tours using
           solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else
            printSolution(sol);

        return true;
    }


    /**
     * A recursive utility function to solve Knight Tour problem
     *
     * @param x     current coordinates.
     * @param y
     * @param movei current move
     * @param sol   current path.
     * @param xMove next possible moves
     * @param yMove
     * @return if can rearch every possible location.
     */
    protected static boolean solveKTUtil(int x, int y, int movei, int sol[][], int xMove[], int yMove[]) {
        int k, next_x, next_y;
        if (movei == N * N)
            return true;

        /* Try all next moves from the current coordinate  x, y */
        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1, sol, xMove, yMove))
                    return true;
                else
                    sol[next_x][next_y] = -1;// backtracking
            }
        }

        return false;
    }

    /* Driver program to test above functions */
    public static void main(String args[]) {
        solveKT();
    }
}