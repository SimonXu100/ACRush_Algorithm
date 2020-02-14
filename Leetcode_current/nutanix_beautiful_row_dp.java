// 两个 dp 可以做。一个先顺着找最长非递减子序列，另一个倒着找最长非递增子序列。之后两个 dp
// 相同 index 和最大，说明那个点就是最高的点。res = n - (dp1[i] + dp2[i] - 1)
 
dp1[i] = dp[i-1] + 1 if heights[i-1] <= heights[i] else dp1[i] = dp1[i-1]
dp2[i-1] = dp[i] + 1 if height[i-1] >= heights[i]  else dp2[i-1] = dp2[i]

res = Math.min (n - dp1[i] + dp2[i] - 1) for every i 



-----------------------------------------------------------------------------------------