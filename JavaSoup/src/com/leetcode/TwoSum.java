package com.leetcode;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TwoSum {

    public static int[] getMaxMin(ArrayList list){
        int[] res = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < list.size(); i++){
            int e = (Integer)list.get(i);
            if (e > res[0]){
                res[0] = e;
            }
            if (e < res[1]){
                res[1] = e;
            }
        }
        return res;
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, ArrayList> map = new HashMap<Integer, ArrayList>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                ArrayList list = new ArrayList();
                list.add(i);
                map.put(nums[i], list);
            }
        }

        int[] result  = new int[2];
        Iterator<Map.Entry<Integer,ArrayList>> iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry<Integer,ArrayList> entry = iter.next();
            int other = target - entry.getKey();
            if (map.containsKey(other)){
                ArrayList list1 = entry.getValue();
                ArrayList list2 = map.get(other);
                int[] maxmin1 = getMaxMin(list1);
                int[] maxmin2 = getMaxMin(list2);
                //if in order
                if (maxmin1[0] != maxmin2[1]){
                    result[0] = maxmin1[0];
                    result[1] = maxmin2[1];
                }else{
                    result[0] = maxmin1[1];
                    result[1] = maxmin2[0];
                }
                // in order
//                if (maxmin1[0] != maxmin2[1]) {
//                    result[0] = maxmin1[0] > maxmin2[1] ? maxmin2[1] : maxmin1[0];
//                    result[1] = maxmin1[0] > maxmin2[1] ? maxmin1[0] : maxmin2[1];
//                } else {
//                    result[0] = maxmin1[1] > maxmin2[0] ? maxmin2[0] : maxmin1[1];
//                    result[1] = maxmin1[1] > maxmin2[0] ? maxmin1[1] : maxmin2[0];
//                }
            }else{
                continue;
            }
        }

        return result;
    }

    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/input.txt").getPath()));
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        //        Scanner sc = new Scanner(System.in);
        //presolve
        //input
//        int[] a = {2, 7, 11, 15};
//        int[] ans = twoSum(a, 9);

        int[] a = {3,3};
        int[] ans = twoSum(a, 6);

        for (int indc : ans) {
            System.out.println(indc);
        }

        //resolve

        //output
        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}

