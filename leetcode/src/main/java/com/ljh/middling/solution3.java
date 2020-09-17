package com.ljh.middling;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长字符串
 *
 * @author user
 */
public class solution3 {

    /**
     * 暴力解决
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int ans =0;
        for (int i = 0; i < s.length(); i++) {
            for (int j=i+1;j<s.length();j++){
                if(allUnique(s,i,j)) {
                    ans = Math.max(ans,j-i);
                }
            }
        }
        return ans;
    }

    /**
     * 滑窗解决
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int ans =0;
        int i = 0;int j=0;
        Set<Character> set = new HashSet<>();
        while (i<s.length()&&j<s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans,j-i);
            }
            else{
              set.remove(i++);
            }
        }
        return ans;
    }

    /**
     * hashMap滑窗解决
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int ans =0;
        int i = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)),i);
            }
            ans = Math.max(ans,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return ans;
    }


    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }


    public static void main(String[] args) {
       String s = "abcdabcbb";
        System.out.println(lengthOfLongestSubstring3(s));

    }
}
