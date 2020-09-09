//动态规划
//interval programming
//动态规划
//区间规划
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][]dp = new int[n][n];
        //dp矩阵：dp[i][j]：表示从第i个字符到第j个字符之间的最长回文字符序列
        //初始化：对角线赋值为1，表示单个字符的回文长度为1
        for(int i = 0;i<n;i++) dp[i][i] = 1;
        //长度增长为外部循环，但是可能有不同的序列
        for(int len =2;len<=n;len++){
            //自底向上不断递推
            for(int i = 0;i<n-len+1;i++){
                int j = i+len-1;
                //比较考虑序列串中，在扩大过程中，每次得出区间起始位置i和结束位置j
                //比较首尾字符，若相等，则回文序列长度增加两个
                //若不相等，问题规模缩小为两种可能i---j-1,i+1---j
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = 2+(len==2?0:dp[i+1][j-1]);
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
