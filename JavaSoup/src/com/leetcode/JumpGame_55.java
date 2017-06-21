package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JumpGame_55 {

    /**
     * SPCS, bfs in graph.
     * Imagination, graph.
     * <p/>
     * Think this problem to be a graph pro, element in the array is the node.
     * edge indicates if the element could jump to the other.
     * so the problem reduced to test if the last node connected to the graph.
     * A bfs or dfs is the soluton.
     * And the nums array is a natural queue for the bfs, so bfs is better.
     * one trick is treat the nums as a natural queue, which reduced numbers of enqueue
     * and popqueue operations, so the time complexity is reduced to O(n).
     * <p/>
     * Another to notice is that the nums array is actually the toplogical order of the
     * graph (a DAG actually), so the shortest path method based on toplogical order(simplified from the bellman-ford algo)
     * could be used to sovle the
     * problme by the path length of the last node, INF means unreachable, otherwise reachable.
     * but the time complexity is O(E+V), that is n*M, same for the bruete force method in Jump Game 2.
     * So this method is still unfeasible.
     * <p/>
     * O(n)
     * AC
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) return true;
        int step = 1;
        int maxrange = nums[0], nextrange = nums[0];
        //nextrange is the current queue end, that is the temp boundary of the next level.
        int i;
        for (i = 1; i < n; i++) {
            if (i > nextrange) {
                break;
            }
            if (i > maxrange) {//the new step
                maxrange = nextrange;//maxrange is the current level or step boundary.
                step++;
            }
            nextrange = Math.max(nextrange, i + nums[i]);
        }

        return i == n;
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
        System.out.print(new JumpGame_55().canJump(new int[]{3, 2, 1, 0, 4}));

        //output

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}
