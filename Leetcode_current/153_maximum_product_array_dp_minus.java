// DP
// there exists negative number
// method 1
// for minus existing, maintaining max and min value which is a minus value
// dpMax[i]: 用 dpMax[i] 表示以第 i 个元素的结尾的子数组，乘积最大的值，也就是这个数组必须包含第 i 个元素
// divide into suboptimal: the subarray with largest product must be one of the situation ending in the ith element
// control contiguous
// transfer equation: 
// minus value tranpose, dpMax[i-1] * nums[i](contiguous), nums[i](discontinuous)
// dpMax[i] = math.max(dpMin[i-1] * nums[i], dpMax[i-1]* nums[i], nums[i])
// dpMin[i] = math.min(dpMin[i-1] * nums[i], dpMax[i-1]*nums[i], nums[i])
// max = math.max(dpMax[i], max)
/*
class Solution {
    
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        
        int max = nums[0];
        for(int i=1; i<n; i++){
            dpMax[i] = Math.max( dpMin[i-1]*nums[i], Math.max(dpMax[i-1]*nums[i], nums[i]));
            dpMin[i] = Math.min( dpMin[i-1]*nums[i], Math.min(dpMax[i-1]*nums[i], nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;   
    }
}
*/

// optimization 1: for space
// running time: O(n)
// space: O(1)
/*
class Solution {
    
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int tempMax = nums[0];
        int tempMin = nums[0];
    
        int max = nums[0];
        for(int i=1; i<n; i++){
            int preMax = tempMax;
            tempMax = Math.max( tempMin*nums[i], Math.max(tempMax*nums[i], nums[i]));
            tempMin = Math.min( tempMin*nums[i], Math.min(preMax*nums[i], nums[i]));
            max = Math.max(max, tempMax);
        }
        return max;   
    }
}
*/

// optimization 2: for computing
// the max value could be turn into min value if meeting minus environment
class Solution {
    
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int tempMax = nums[0];
        int tempMin = nums[0];
    
        int max = nums[0];
        for(int i=1; i<n; i++){
            if(nums[i] <= 0){
                int temp = tempMax;
                tempMax = tempMin;
                tempMin = temp;   
            }
            tempMax = Math.max( tempMax*nums[i], nums[i]);
            tempMin = Math.min( tempMin*nums[i], nums[i]);
            max = Math.max(max, tempMax);
        }
        return max;   
    }
}













