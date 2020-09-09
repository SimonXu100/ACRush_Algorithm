//leetcode答案尝试
//相同循环方法，双指针常用方法
/*
public class Solution {
    public int lengthOfLongestSubstring(String s) {
       int n = s.length();
       int i = 0,j=0;
       int max = 0;
       Set<Character> set = new HashSet<>();
       while(i<n&&j<n){
          if(!set.contains(s.charAt(j))){
              set.add(s.charAt(j++));
              max = Math.max(max,j-i);
              //max = Math.max(max,set.size());
          }
          else{
              set.remove(s.charAt(i++));
          }
       }
        return max;
    }
}
*/


//300示例答案
/*
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        Set<character> set = new HashSet<>();
        for(int i =0, j =0;j<s.length();j++){
            while(set.contains(s.charAt(j))){
               set.remove(s.charAt(i));
               i++;
            }

            set.add(s.charAt(j));
            max = Math.max(max,set.size());
        }
        return max;
    }
}
*/



//优化的线性方法，hashmap直接跳到指定位置
//时间复杂度：O（N）
//空间复杂度：the worst: O(n)

//300给的答案
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        Map<Character,Integer> hashmap = new HashMap<>();
        //hashmap 记录上次最新某字符出现位置
        for(int i =0,j=0;j<s.length();j++){
           if(hashmap.containsKey(s.charAt(j))){
               i = Math.max(i,hashmap.get(s.charAt(j))+1);
           }
           //快指针字符参加到hash表中
           hashmap.put(s.charAt(j),j);
           //更新结果max
           max = Math.max(max,j-i+1);
        }
        return max;
    }
}






















