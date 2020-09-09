// DP
// transfer equation: sep[0,i] =  sep[0,j] && contains(s.substring(j,i))
// for j < i, i from 0 to n -1
// every word might be a valid step of sub optimal structure
// running time: O(n^2)
// space: O(n)

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        // initialization: for consistency of the form
        for(int i = 1; i<=n; i++){
            for(int j=0; j<i;j++){
                if(dp[j] && wordDictSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;  
                }
            }
        }
        return dp[n];
    }
}


// method 2 BFS
// BFS: search method, step: any one possible word
// if the (start, end) is an appropriate segment, and end reach the end, then must be all string are separable
// running time:O(n^2) for every start, will finally reach the end
// space: O(n)
/*
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        int n = s.length();
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[n];
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.poll();
            if(visited[start] == 0){
                for(int end=start+1; end<=n; end++){
                    if(wordDictSet.contains(s.substring(start,end))){
                        queue.offer(end);
                        if(end == n) return true;
                    }  
                } 
                visited[start] = 1;
            }
        }
        return false;    
    }
}
*/

// method 3 recursion with memorization

// basic brute force with recursion
// check all possible result, if all segment could be separable, then finally true
// runningt time: n^n
// space: O(n)
/*
class Solution {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak_helper(s, new HashSet(wordDict), 0);
    }
    
    public  boolean wordBreak_helper(String s, Set<String> wordDict, int start){
        if(start == s.length()) return true;
        for(int end = start+1; end<=s.length(); end++){
            if(wordDict.contains(s.substring(start,end)) && wordBreak_helper(s, wordDict, end)){
                return true;
            }
        }
        return false;
    }
}
*/

// recursion optimization with memory
// 每当访问到已经访问过的后缀串，直接用 memomemo 数组中的值返回而不需要继续调用函数
// running time: O(n)
// space: O(n)
/*
class Solution {
    // if separable starting from index
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new Boolean[s.length()];
        return wordBreak_helper(s, new HashSet(wordDict), 0);     
    }
    
    public boolean wordBreak_helper(String s, Set<String> wordDict, int start){
        if(start == s.length()){
            return true;
        }
        // suffix content
        if(memo[start] != null){
            return memo[start];
        }
        for(int end = start+1; end<=s.length(); end++){
            if(wordDict.contains(s.substring(start,end)) && wordBreak_helper(s, wordDict, end)){
                memo[start] = true;
                return true;
            }
        }
        return memo[start] = false;
    }   
}
*/


// function: Hashset
// Set<String> wordDictSet = new HashSet(wordDict);
// wordDict: list of string
// wordDictSet.contains(string)


// java knowledge
// Boolean, could be used to represent object. when initialization, the element of it is null, not default value
