// sliding window
// notes: keep the sequence  of T
/*
class Solution {
    public String minWindow(String S, String T) {
        int formed = 0;
        int min_length = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int[] minWindow = new int[]{left, right};
        
        int s_length = S.length();
        int t_length = T.length();
        int next = 0;
        while(right<s_length){
            // expansion
            char cur = S.charAt(right);
            if(T.charAt(formed) == cur){
                formed++;
            }
            // skipping, 剪枝
            if(formed == 1 && cur == T.charAt(0)) next = right;
            // contraction when formed is valid
            while(left<=right && formed == t_length){
                if(right-left+1 < min_length ){
                    min_length = right - left + 1;
                    minWindow[0] = left;
                    minWindow[1] = right;
                }
                if(left < next){
                    left = next;
                }
                else{
                    formed = 0;
                    left++;
                    right = left-1;
                    break;
                }
            }
            right++;    
        }
        return min_length != Integer.MAX_VALUE? S.substring(minWindow[0],minWindow[1]+1): "";
    }
}
*/



// 正向扩展， 反向contraction 
// method 2
// running: O(n^2)
/*
class Solution {
    public String minWindow(String S, String T) {
        int min_length = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int[] minWindow = new int[]{left, right};
        
        int s_length = S.length();
        int t_length = T.length();

        char[] s_chars = S.toCharArray();
        char[] t_chars = T.toCharArray();

        // record the step in String T
        int formed = 0;
        while(right < s_length){
            // expansion
            char cur = s_chars[right];
            if(cur == t_chars[formed]) formed++;

            // contraction
            // 反向tracking
            if(left<=right && formed==t_length){
                formed--;
                int temp_right = right;
                while(formed >= 0 ){
                    if(s_chars[right] == t_chars[formed])
                    formed--;
                    right--;
                }
                right++;
                left = right;
                if(temp_right-left+1 < min_length ){
                    min_length = temp_right - left + 1;
                    minWindow[0] = left;
                    minWindow[1] = temp_right;
                } 
                formed = 0;
            }
            right++;
        }
        return min_length != Integer.MAX_VALUE? S.substring(minWindow[0],minWindow[1]+1): "";   
    }
}
*/


// method 3
// DP
// 最长公共子序列
// 找到某一个区间内， 长度等于3的值
// recursion equation
// notes: DP 还是要考虑清楚子结构的变化，结合子结构得出转换方程
// 此题，因为是求子序列， 所以关键点在与起点的控制变化
// 再反方向回归（考虑结尾字符的匹配），决定起点的变化方式
// DP[i,j]: 满足题意的T[:i] 和S[:j] 的起始点index
// 子串的位置为S[index :j]. index = DP[i,j]
// 转移方程： DP[i，j] = DP[i-1,j-1] if T[i] = S[j]: 方向匹配
// otherwise: DP[i,j] = DP[i,j-1]

// 初始化： if i== 0 : DP[0,j] = j
// if j== 0; DP[i,0] == 0

// 最小窗口控制：
// min(j - DP[length(T),j]
// running time: O(n^2)
// space: O(n^2)
class Solution {
    public String minWindow(String S, String T) {
        int min_length = Integer.MAX_VALUE;
        int s_length = S.length();
        int t_length = T.length();

        char[] s_chars = S.toCharArray();
        char[] t_chars = T.toCharArray();
        int left = 0;
        int right = Integer.MAX_VALUE;
        
        // memorization
        int[][] dp = new int[t_length+1][s_length+1];
        // initialization
        for(int j=0; j<s_length+1; j++){
            dp[0][j] = j+1;
        }

        for(int i =1; i<t_length+1; i++){
            for(int j =1; j<s_length+1; j++){
                if(t_chars[i-1] == s_chars[j-1]) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = dp[i][j-1];
            }
        }
        
        // select the minimun value
        for(int j=1; j<s_length+1; j++){
            if(j- dp[t_length][j] + 1 < min_length){
                if(dp[t_length][j] > 0){
                    min_length = j- dp[t_length][j] + 1;
                    left = dp[t_length][j]-1;
                    right  = j;
                }
            }
        }
        return min_length != Integer.MAX_VALUE? S.substring(left, left+min_length): "";
    }
}



































