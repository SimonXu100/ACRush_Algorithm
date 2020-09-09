//sliding window
//长度动态变化的连续空间
//string：决定是否转化成数组
//需要快速查找某元素是否存在----HashMap
//计算区间长度的方法：双指针，或者入队等数据结构下标

//双指针
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //the longest number
        int maxCount = 0;
        //int count = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int j = 0, i = 0; j<s.length();j++){
            if(map.containsKey(s.charAt(j))){
                maxCount = Math.max(maxCount,j-i);
                //自增条件判定不合理
                if(map.get(s.charAt(j))<i){
                    maxCount = Math.max(maxCount,j-i+1);
                }
                i = Math.max(i,map.get(s.charAt(j))+1);
                map.put(s.charAt(j),j);
            }
            else{
                maxCount = Math.max(maxCount,j-i+1);
                map.put(s.charAt(j),j);
            }
        }
        return maxCount;
    }
}

///sliding window
//长度动态变化的连续空间
//string：决定是否转化成数组
//需要快速查找某元素是否存在----HashMap
//计算区间长度的方法：双指针，或者入队等数据结构下标
//双指针
//修改
/*
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //the longest number
        int maxCount = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int j = 0, i = 0; j<s.length();j++){
            if(map.containsKey(s.charAt(j))&&map.get(s.charAt(j))>=i){
                maxCount = Math.max(maxCount,j-i);
                //或者去掉判断掉件右边，加上以下注释部分
                //if(map.get(s.charAt(j))<i){
                //    maxCount = Math.max(maxCount,j-i+1);
                //}
                i = Math.max(i,map.get(s.charAt(j))+1);
            }
            else{
                maxCount = Math.max(maxCount,j-i+1);
            }
            map.put(s.charAt(j),j);
        }
        return maxCount;
    }
}
*/

//尝试1：字符串改为数组，提高访问速度？:相差不多
//
/*
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //the longest number
        int maxCount = 0;
        char[] arr = s.toCharArray();
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int j = 0, i = 0; j<arr.length;j++){
            if(map.containsKey(arr[j])&&map.get(arr[j])>=i){
                maxCount = Math.max(maxCount,j-i);
                i = Math.max(i,map.get(arr[j])+1);
            }
            else{
                maxCount = Math.max(maxCount,j-i+1);
            }
            map.put(arr[j],j);
        }
        return maxCount;
    }
}
*/

//尝试2：固定长度数组：根据元素类型: 利用ascII码
//同时也可以尝试删除记述
//从头递归发生错误
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //the longest number
        int maxCount = 0;
        int[] index = new int[128];
        Arrays.fill(index, -1);
        for(int j = 0, i = 0; j<s.length();j++){
            //不存在与位置在0产生逻辑冲突
            if(index[s.charAt(j)]>=i){
                maxCount = Math.max(maxCount,j-i);
                i = Math.max(i,index[s.charAt(j)]+1);
            }
            else{
                maxCount = Math.max(maxCount,j-i+1);
            }
            index[s.charAt(j)] = j;
            
            //整合一致
            /*
            i = Math.max(index[s.charAt(j)], i);
            maxCount = Math.max(maxCount, j - i + 1);
            index[s.charAt(j)] = j + 1;
            */
        }
        return maxCount;
    }
}

//官方解答
/**
//如果列举类型比较少，可以采用固定长度数组
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
*/

/**
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
*/


