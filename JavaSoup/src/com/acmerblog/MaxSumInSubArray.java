package com.acmerblog;

/**
 * Created by Qingwei on 2017/3/10.
 */
public class MaxSumInSubArray {

    //2 pointers简化版, o(n),
    //wrong algo
    public static int MaxSum(int arr[], int n) {
        int currentSum = arr[0];
        int ans = currentSum;
        for (int i = 1; i < n; i++) {
            currentSum = Math.max(currentSum + arr[i], arr[i]);
            ans = Math.max(ans, currentSum);
        }
        return ans;
    }

    /**
     * 2 pointers, SPCS
     * <p/>
     * o(n)
     * AC
     *
     * @param arr
     * @param n
     * @return
     */
    public static Result maxSum2Pointer(int arr[], int n) {
        int start = 0, end = 0;
        int max_sum = Integer.MIN_VALUE, s_idx = -1, e_idx = -1;
        int t_sum = 0;
        while (start < n && end < n) {
            t_sum += arr[end];
            //store info for the max subarray
            if (t_sum > max_sum) {
                s_idx = start;
                e_idx = end;
            }
            max_sum = Math.max(max_sum, t_sum);
            if (t_sum < 0) {
                t_sum = 0;
                start = end + 1;
            }
            end++;
        }
        return new Result(s_idx, e_idx, max_sum);
    }


    /**
     * base algo, DC
     * Merge is the key operation in the dc algo.
     * The subarray of the maximum length could be omitted when it cross the two divisions.
     * So we check this possibility by scan form the start of the start maxi-sum array to the
     * end of the end maxi-sum array.
     * This is because the start on the start of the start maxi-sum array is helpless(that is negtive)
     * to maximum the sum of the searching, so is the end on the end of the end of end maxi-sum array.
     * In the scan process, thought is the same as the 2 pointers technique, give the start location
     * when the temporary sum is negtive.
     * <p/>
     * Three special situation here,
     * the better one is combine the both maxi-sum array and the middle some negtive value to get the maxi-sum.
     * the 2 is just combine, because the they are adjacent.
     * the 3 is the the middle negtive value is much negtive that the combine could not generate a better one.
     * All this 3 situations could be handled perfectly.
     * <p/>
     * O(nlogn)
     * AC
     *
     * @param arr
     * @param n
     * @return sum, start, end
     */
    public static Result maxSum_DC(int arr[], int n) {
        int ans = Integer.MIN_VALUE;
        Result rest = maxSum_DC_recursive(arr, n, 0, n - 1);
        return rest;
    }

    private static Result maxSum_DC_recursive(int[] arr, int n, int start, int end) {
        if (start == end) {
            return new Result(start, end, arr[start]);
        }

        int mid = (start + end) / 2;
        Result res1 = maxSum_DC_recursive(arr, n, start, mid);
        Result res2 = maxSum_DC_recursive(arr, n, mid + 1, end);
        //merge
        //find the rightmost maximus sum in the start one
        int idx = res1.end + 1;
        int idx1 = res1.start;
        int sum1 = res1.sum;
        while (idx <= mid) {
            sum1 += arr[idx];
            if (sum1 < 0) {
                sum1 = 0;
                idx1 = idx + 1;
            }
            idx++;
        }
        //find the end half
        idx = res2.start - 1;
        int idx2 = res2.end;
        int sum2 = res2.sum;
        while (idx >= mid + 1) {
            sum2 += arr[idx];
            if (sum2 < 0) {
                sum2 = 0;
                idx2 = idx - 1;
            }
            idx--;
        }

        //compare and return
        int sum = (sum1 > 0 && sum2 > 0) ? sum1 + sum2 : Integer.MIN_VALUE;
        if (sum > res1.sum && sum > res2.sum) {
            return new Result(idx1, idx2, sum);
        } else {
            return res1.sum > res2.sum ? res1 : res2;
        }
    }

    public static class Result {
        public int start;
        public int end;
        public int sum;

        Result(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
//        int a[] = {1, 2, 3, -1, -3, 3, -4, 5};
        int a[] = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};
        Result rest = maxSum2Pointer(a, a.length);
        System.out.println(rest.sum + "\t" + rest.start + "\t" + rest.end);
        int res = MaxSum(a, a.length);
        System.out.println(res);
        rest = maxSum_DC(a, a.length);
        System.out.println(rest.sum + "\t" + rest.start + "\t" + rest.end);
    }
}
