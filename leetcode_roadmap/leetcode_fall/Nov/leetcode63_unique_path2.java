// 注意空间复杂度的优化
// 动态规划， 注意滚动数组的优化
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 滚动数组优化
        int[] dp = new int[obstacleGrid[0].length];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for(int i=0; i<obstacleGrid.length; i++){
           for(int j=0; j<obstacleGrid[0].length; j++){
               if(obstacleGrid[i][j] == 1) dp[j] = 0;
                else if(j-1 >=0 && obstacleGrid[i][j-1] == 0){
                    // 其中包含了叠加了上边的变量
                    dp[j] += dp[j-1];
                }
           }
        }
        return dp[obstacleGrid[0].length-1];
    }
}