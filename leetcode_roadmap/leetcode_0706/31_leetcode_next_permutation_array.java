// brute force
// running time: O(n!)
// space: O(N)
// find all possible permutation
// select the one larger than that number with smallest step


// method 1 
// array
// one pass
// two steps
// 降序的序列的不存在下一个排列
// 反向扫描
// running time; O()
class Solution {
    public void nextPermutation(int[] nums) {
        // from the rear side, find the first element smaller than its next element
        int i = nums.length - 2;
        while( i>=0 && nums[i+1] <= nums[i]){
            i--;
        }
        
        // from the right to left, find the first element larger than the current value
        if(i >= 0){
            int j = nums.length - 1;
            while(j>=0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);   
        }
        // then reverse all next values, then we get the result 
        reverse(nums, i+1);
    }
    
    // consecutive decreasing using reverse
    void reverse(int[] nums, int start){
        // double pointers
        // swap from both sides
        int l = start, r = nums.length-1;
        while(l<r){
            swap(nums, l, r);
            l++;
            r--;
        }   
    }
    
    void swap(int[] nums, int i, int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;       
    }
}
