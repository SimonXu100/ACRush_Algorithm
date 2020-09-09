// method 1: hashmap, double pointers, sliding windows
// notes: modify the count(value) of a key to represent adding or decreasing
//running time: 快慢指针遍历字符一遍，时间复杂度O(n), hashmap operation: O(1)
//空间复杂度： 运用了一个map来统计，空间复杂度O(n)
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // 初始化一个HashMap,用来统计出现的不同字符
        HashMap<Character, Integer> map = new HashMap<>();
        // 用max 变量记录最长的子串，其中最多包含K个不同的字符
        int max = 0;
        // 用快慢指针遍历字符串
        for(int i=0, j=0; j<s.length(); j++){
            char cj = s.charAt(j);
            //step1: count the character
            // 将快慢指针指向的字符加入到map中，统计字符出现的次数
            map.put(cj, map.getOrDefault(cj,0)+1);
            // step2: clean up the map if condition does not match
            while(map.size()>k){
                //获取慢指针指向的字符
                char ci = s.charAt(i);
                map.put(ci, map.get(ci)-1);
                // 统计次数为0， 从map删除
                if(map.get(ci)==0){
                    map.remove(ci); 
                }
                i++;
            }
            // step3: condition matched, now update the result
            max = Math.max(max,j-i+1);
        }
        // 返回最大子串数
        return max;
    }
}

// method 2: OrderDict/ LinkedHashMap 
// similar to LRU 缓存机制
// for the convience to delete the leftmost character