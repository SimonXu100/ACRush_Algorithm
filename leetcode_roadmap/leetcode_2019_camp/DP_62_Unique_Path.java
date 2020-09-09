//动态规划
/*
class Solution {
    public int uniquePaths(int m, int n) {
       int[][] dp = new int[n][m];
       if(m==0&&n==0){
           return 0;
       }
       dp[0][0] = 1;
       //初始化
       for(int k = 1;k<m;k++){
           dp[0][k] = 1;
       }
       for(int l = 1;l<n;l++){
           dp[l][0] = 1;
       }
       for(int i = 1;i<n;i++){
           for(int j = 1;j<m;j++){
               
               if(j==0){
                   dp[i][j] = dp[i-1][j];
                   continue;
               }
               if(i==0){
                   dp[i][j] = dp[i][j-1];
                   continue;
               }
               dp[i][j] = dp[i-1][j] + dp[i][j-1];
               System.out.println(dp[i][j]);
           }
       }
       return dp[n-1][m-1];
    }
}
*/
//动态规划简洁化

class Solution {
    public int uniquePaths(int m, int n) {
       int[][] dp = new int[n][m];
       if(m==0&&n==0){
           return 0;
       }
       dp[0][0] = 1;
       for(int i = 0;i<n;i++){
           for(int j = 0;j<m;j++){
               if(j==0){
                   dp[i][j] = 1;
                   continue;
               }
               if(i==0){
                   dp[i][j] = 1;
                   continue;
               }
               dp[i][j] = dp[i-1][j] + dp[i][j-1];
           }
       }
       return dp[n-1][m-1];
    }
}
//
//列举排列组合方法即可
