// 大局观。 先问题本质


// all 0 or 1 group together
// double pointer
// every group have same number of 0 and 1
// method 1: group together
// in a qualified group, if the size of group is not equal to 2, then the size of group could be got shrink
// the qualified substring got augmented
// running time: O(n)
// space: O(1)

class Solution {
    public int countBinarySubstrings(String s) {
        if(s == null || s.length() == 0) return 0;
        // build group array: store the number of consective number 
        // array or arraylist
        int[] groups = new int[s.length()];
        int t = 0; // the size of group for elements that have been used
        groups[0] = 1;
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != s.charAt(i-1)){
                t++;
                groups[t] = 1;
            } 
            else{
                groups[t]++;
            }
        }
        // every consecutive group could be divided into min(groups[i-1], groups[i]) qualified substrings
        int ans = 0;
        for(int i=0; i<t; i++){
            ans = ans + Math.min(groups[i], groups[i+1]);
        }
        
        return ans;    
    }
}


// method 2 linear scanning
// optimization for concise
// unsing prev and cur, record groups that departs from 1 and 2 
/*
class Solution {
    public int countBinarySubstrings(String s) {
        if(s == null || s.length() == 0) return 0;
        int prev = 0;
        // always has one group
        int cur = 1;
        int ans = 0;
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != s.charAt(i-1)){
                ans = ans + Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } 
            else{
                cur++;
            }
        }
        // last step do not count
        return ans + Math.min(prev, cur);
    }
}
*/

// method 3 
// attempt, double pointer
/*
class Solution {
    public int countBinarySubstrings(String s) {
        
        
    }
}
*/
