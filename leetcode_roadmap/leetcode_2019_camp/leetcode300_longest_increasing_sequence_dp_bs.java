// 动态规划 O(n^2)
// 定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
// 我们从小到大计算 dp[] 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0 ... i-1]dp[0…i−1] 的值，则状态转移方程为：
// dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
// method 1
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] cache = new int[n];
        // result: the length of longest increasing sequence
        int max = 0;
        // initialization
        for(int i=0; i<n;i++){
            cache[i] = 1;
        }
        for(int i=0; i<n;i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i] && cache[i] < cache[j]+1){
                    cache[i] = cache[j] + 1;
                }
            }
            max = cache[i] > max? cache[i]:max;
        }
        return max;
    }
}
*/

//method 2: O(nlgn)
// greedy and binary search
/*
同时我们可以注意到 d[i] 是关于 i 单调递增的。因为如果 d[j]≥d[i] 且j<i，我们考虑从长度为 i 的最长上升子序列的末尾删除 i−j 个元素，那么这个序列长度变为 j ，且第 j 个元素 x（末尾元素）必然小于 d[i]，也就小于 d[j]。那么我们就找到了一个长度为 j 的最长上升子序列，并且末尾元素比 d[j] 小，从而产生了矛盾。因此数组 d[] 的单调性得证。

我们依次遍历数组 nums[] 中的每个元素，并更新数组 d[] 和 len 的值。如果 \textit{nums}[i] > d[\textit{len}]nums[i]>d[len] 则更新 len = len + 1，否则在 d[1…len]中找满足 d[i−1]<nums[j]<d[i] 的下标 i，并更新 d[i]=nums[j]
*/
// idea: 单调性
// 我们维护一个数组 d[i] ，表示长度为 i 的最长上升子序列的末尾元素的最小值，用 len 记录目前最长上升子序列的长度，起始时 len 为 11，d[1]=nums[0]
//我们依次遍历数组 nums[] 中的每个元素，并更新数组 d[]和 len 的值。如果 nums[i]>d[len] 则更新 len = len + 1，否则在 d[1…len]中找满足 d[i−1]<nums[j]<d[i] 的下标 i，并更新 d[i]=nums[j]。
//根据 dd 数组的单调性，我们可以使用二分查找寻找下标 i，优化时间复杂度
// method 2.1
// self-defined binary search by iteration
// using  binary search find the first element index less than 
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int len = 0;
        dp[len] = nums[0];
        len++;
        for (int num : nums) {
            if(num > dp[len-1]){
                dp[len] = num;
                len++;
            }
            else{
                // binary search by iteration
                int left = 0;
                int right = len-1;
                int mid = 0;
                int pos = 0;
                // 刚好找到合适的范围
                while(left<=right){
                    mid = left + (right - left)/2;
                    if(dp[mid] >= num){
                        pos = mid;
                        right = mid - 1;
                    }
                    else{
                        left = mid + 1;
                    }
                }
                // 已经保证小于 dp[len]
                dp[pos] = num;
            }
        }
        return len;
    }
}
*/
// binary search by collections
/*
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // 从1开始计数
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}*/

public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = {2,4,5,6,7};
        // 寻找插入点
        //  -2
        // -（-2+1） = 1: 插入点
        int res = Arrays.binarySearch(dp, 0, 5,3 );
        System.out.println(-(res+1));
        return res;
    }
}



