package com.ljh.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和，给定一个整数组和一个目标值，找出数据中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复使用。
 * 给定nums=[2,7,11,15],target=9,因为num[0]+num[1] =2+7= 9
 * 所以返回[0,1]
 */
public class solution1 {

    //空间换时间，空间小，时间长
    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        if (a.length > 0) {
            return a;
        }
        return null;
    }

    // 空间大，时间小
    public static int [] twoSum2(int[] nums,int target) throws IllegalAccessException {
        int[] a = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <nums.length; i++) {
            map.put(nums[i],i);
        }
        for(int i =0;i<nums.length;i++){
            int complement = target-nums[i];
            if(map.containsKey(complement)&&map.get(complement)!=i){
                return new int[]{i,map.get(complement)};
            }
        }
          throw  new IllegalAccessException("No two sum solution");
    }


    public static int[] twoSum3(int[] nums,int target)throws  Exception{
        Map<Integer,Integer> map =  new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            int complement = target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            else {
                map.put(nums[i],i);
            }
        }
        throw  new IllegalAccessException("No two sum solution");
    }

    public static void main(String[] args) throws Exception {
        int nums[] = {2, 7, 11, 15,16,18,19,20,21,23,24,35,67,78,79,81,82,85,98,100};
        int target = 18;
        Long start = System.currentTimeMillis();
        int b[] = twoSum3(nums, target);
        Long end = System.currentTimeMillis();
        System.out.println("第三种"+(end-start));

        Long start1 = System.currentTimeMillis();
        twoSum(nums, target);
        Long end1 = System.currentTimeMillis();
        System.out.println("第一种"+(end1-start1));

        Long start2 = System.currentTimeMillis();
        twoSum2(nums, target);
        Long end2 = System.currentTimeMillis();
        System.out.println("第二种"+(end2-start2));

        for (int c : b) {
            System.out.println(c);
        }
    }
}
