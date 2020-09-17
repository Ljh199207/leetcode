package com.ljh.simple;

/**
 * @author ljh
 * @date 2019-11-18 14:56
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为O(log(m+n))O(log(m + n))O(log(m+n))。
 */
public class Solution3 {


    public static Double findMedianSortedArrays(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int len = len1 + len2;
        int[] a = new int[len];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (num1[i] < num2[j]) {
                a[k++] = num1[i++];
            } else {
                a[k++] = num2[j++];
            }
        }
        if (i == len1) {
            while (j < len2) {
                a[k++] = num2[j++];
            }
        }
        if (j == len2) {
            while (i < len1) {
                a[k++] = num1[i++];
            }
        }
        if (len > 1) {
            if (len % 2 == 0) {
                return (a[len / 2] + a[len / 2 - 1]) / 2.0;
            } else {
                return a[(len) / 2] / 1.0;
            }
        } else {
            return a[len - 1] / 1.0;
        }
    }

    public static void main(String[] args) {
        int a[] = {1, 3, 4, 9};
        int b[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(findMedianSortedArrays2(a, b));
    }

    //  1,1,2,3,3,4,4,5,6,7,8,9,9,10
    public static Double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        return (getk(nums1, 0, n - 1, nums2, 0, m - 1, left) + getk(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    public static int getk(int[] num1, int start1, int endl, int[] num2, int start2, int end2, int k) {
        int len1 = endl - start1 + 1;
        int len2 = end2 - start2 + 1;
        //num1长度最短
        if (len1 > len2) {
            getk(num2, start2, end2, num1, start1, endl, k);
        }
        if(len1==0){
            return num2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(num1[start1], num2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (num1[i] > num2[j]) {
            return getk(num1, start1, endl, num2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getk(num1, i + 1, endl, num2, start2, end2, k - (i - start1 + 1));
        }
    }


}
