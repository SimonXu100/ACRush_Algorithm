/*
f(i,j)={ min(f(i+1,j−1),f(i,k)+f(k+1,j))
         min(f(i,j),f(i,k)+f(k+1,j))
​}

if(i == j) dp[i][j] = 1
if(i == j -1) dp[i][j] = 2
*/


// method 1
// DP programming
// running time: O(n^3)
// f(i,j)={ min(f(i+1,j−1),f(i,k)+f(k+1,j))
//  min(f(i,j),f(i,k)+f(k+1,j)

// note: 大的范围以小的范围为基础
// 注意在更新大范围之前， 内部的小范围全部更新过
// 从右往左扩展
class Solution {
    public int minimumMoves(int[] arr) {
        // dp[i][j]: store the minimal operations between i and j
        int n = arr.length;
        int[][] dp = new int[n][n];
        // initialization
        for(int i=0; i<n; i++){
            dp[i][i] = 1;
        }

        // if(i == j) dp[i][j] = 1
        // if(i == j-1) dp[i][j] = 2 when a[i] != a[j]
        // if(i == j-1) d[i][j] = 2 when
        // 遍历方向：从下往上、从左往右遍历
        // or 从左往右， 从下往上
        // 向两边扩展的思路
        // int j = 1;  j<n; j++
        // int i = j-1; i>=0; i--
        for(int i=n-1; i>=0; i-- ){
            for(int j=i+1; j<n; j++){
                if(i == j-1){
                    dp[i][j] = arr[i] == arr[j]? 1 : 2;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                if(arr[i] == arr[j]){
                    min = dp[i+1][j-1];
                }
                for(int k=i; k<j;k++){
                    min = Math.min(min, dp[i][k] + dp[k+1][j]);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][n-1];
    }
}




