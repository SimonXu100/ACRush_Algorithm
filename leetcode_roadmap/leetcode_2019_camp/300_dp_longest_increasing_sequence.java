//递归，暴力法
//f(n):含有nums[n-1]为末尾的最长上升子序列
//状态方程：f(n) = max{f(i)}+1:{1<=i<=n-1且nusm[i-1]<nusm[n-1]}
//初始停止状态: length = 0: 无此序列
//length = 1: 返回1
//时间复杂度：O（2^n）
/*
class Solution {
    int max;
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        max = 1;
        helper(nums,nums.length);
        return max; 
    }
    public int helper(int[] nums,int n){
        if(n<=1){
            return n;
        }
        int result = 0;
        //maxEndingHere:包含当前最后一个元素，最长的上升子序列长度
        int maxEndingHere = 1;
        
        for(int i =1; i<n;i++){
            result = helper(nums,i);
            if(nums[i-1]<nums[n-1]&&result+1>maxEndingHere){
                maxEndingHere = result+1;
            }
        }
        //更新max
        if(max<maxEndingHere){
            max = maxEndingHere;
        }
        return maxEndingHere;
    }  
}
*/
//递归优化
//记忆化递归
//hashTable,在递归之前访问hash表中是否有数据，有则不再递归
//O(n^2)
/*
class Solution {
    int max;
    HashMap<Integer,Integer> hashMap;
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        max = 1;
        hashMap = new HashMap<Integer,Integer>();
        helper(nums,nums.length);
        return max; 
    }
    public int helper(int[] nums,int n){
        if(hashMap.containsKey(n)){
            return hashMap.get(n);
        }
        if(n<=1){
            return n;
        }
        int result = 0;
        //maxEndingHere:包含当前最后一个元素，最长的上升子序列长度
        int maxEndingHere = 1;
        
        for(int i =1; i<n;i++){
            result = helper(nums,i);
            if(nums[i-1]<nums[n-1]&&result+1>maxEndingHere){
                maxEndingHere = result+1;
            }
        }
        //更新max
        if(max<maxEndingHere){
            max = maxEndingHere;
        }
        hashMap.put(n,maxEndingHere);
        return maxEndingHere;
    }  
}
*/
//动态规划:自底向上
//数据结构结论结果
//O(n^2)
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        //存储以nums[i-1]结尾的最大上升序列
        int n = nums.length;
        int[] cache = new int[n];
        int i,j,max = 0;
        if(nums.length==0){
            return 0;
        }
        for(i = 0; i<n;i++){
            cache[i] = 1;
        }
        for(i=0;i<n;i++){
            for(j=0;j<i;j++){
                if(nums[j]<nums[i]&&cache[i]<cache[j]+1){
                    cache[i] = cache[j]+1;
                }
            }
            max = Math.max(max,cache[i]);
        }
        return max; 
    }
}
*/
//
//动态规划,二分搜索
//有一定的顺序性

class Solution {
    public int lengthOfLIS(int[] nums) {
        
    }
}
/*
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
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
}
*/
