// method 1: sliding window 
// hashtable
// if fixed number of elements: could use alphabet order, more fast

// Hashtable
// sliding window: two pointers/ 
// brute force: O(n^2)
import java.util.HashMap;
// sliding window: running time: O(n)
// notes: contains all the characters in t, not distinct character in t

class Solution {
    public String minWindow(String s, String t) {
        int s_length = s.length();
        int t_length = t.length();

        if(s_length == 0 || t_length == 0){
            return "";
        }
        // Hashtable: record the number of charaters in S which are also exists in T
        Map<Character, Integer>  hash =  new HashMap<Character, Integer>(); 
        int temp = 0;
        for(int i = 0; i < t_length; i++){
            temp = hash.getOrDefault(t.charAt(i),0);
            hash.put(t.charAt(i), temp+1);
        }
        int size = hash.size();
        int left = 0;
        int right = 0;
        int[] min_window = {left, right};
        int min_length = Integer.MAX_VALUE;

        // sliding window controlling
        // chanllenge: how to maintain if the window is valid: special changing conditions
        int formed = 0;
        // when formed becomes size, the window is valid
        int count = 0;
        // record the word count in a window
        Map<Character, Integer>  window_counts =  new HashMap<Character, Integer>(); 
        while(right<s_length){
            // expansion when the window is not valid(formed < size)
            char cur = s.charAt(right);
            count = window_counts.getOrDefault(cur,0);
            if(hash.containsKey(cur)){
                window_counts.put(cur, count+1);
                if(window_counts.get(cur).intValue() == hash.get(cur).intValue()){
                    formed++;
                }
            }
            // contraction when the formed is valid(formed == size)
            while(left <=right && formed == size){
                if(right-left+1 < min_length ){
                    min_length = right - left + 1;
                    min_window[0] = left;
                    min_window[1] = right;
                }
                cur = s.charAt(left);
                if(hash.containsKey(cur)){
                    count = window_counts.get(cur);
                    window_counts.put(cur,count-1);
                    if(window_counts.get(cur) < hash.get(cur)){
                        formed--;
                    }
                }
                left++;
            }
            right++;
        }
        return (min_length != Integer.MAX_VALUE)? s.substring(min_window[0],min_window[1]+1): "";
    }
}



// method 2: sliding window optimization:
// using filtered 
/*
import javafx.util.Pair;
class Solution {
    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();  
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and co***act the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;   
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
*/




//
//在滑动窗口类型的问题中都会有两个指针。一个用于延伸现有窗口的 rightright指针，和一个用于收缩窗口的leftleft 指针。在任意时刻，只有一个指针运动，而另一个保持静止

// function
// size: hashmap.size() 
// dictT.getOrDefault(t.charAt(i), 0);
//The java.lang.Integer.intValue() is an inbuilt method in java that returns the value of this integer as an int

// hash.get(): return null or other
// hash.getOrDefault: cannot find: return 0
// Integer.intValue(): value change: has some kind of range: 
// import javafx.util.Pair;
// getValue()
// getKey()
// new Pair<Integer, Character>(i, c)
