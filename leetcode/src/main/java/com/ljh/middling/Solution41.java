package com.ljh.middling;

import java.util.Arrays;

/**
 * @author ljh  缺失的第一个正数
 * @date 2020-03-20 08:56
 * 时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class Solution41 {

    public static void main(String[] args) {
        int[] nums = {3, 4, -1, 5};
        System.out.println(firstMissingPositive(nums));

    }


    public static int firstMissingPositive(int[] num) {
        int len = num.length;
        for (int i = 0; i < len; i++) {
            // 前两个是在判断是否成为索引
            while (num[i]>0 && num[i]<=len && num[num[i]-1]!=num[i]){
                swap(num, num[i] - 1, i);
            }
        }
        // 调试代码
         System.out.println(Arrays.toString(num));
        for (int i = 0; i < len; i++) {
            // [1,-2,3,4]
            // 除了 -2 其它都满足：i+1 = num[i]
            if (num[i] - 1 != i) {
                return i + 1;
            }
        }
        return len + 1;
    }
    private static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }



}
