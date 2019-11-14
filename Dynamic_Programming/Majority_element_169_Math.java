class Solution {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count  = 0;
        for( int i = lo; i <=hi; i++){
            if(nums[i] == num) count++;
         }
         return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
       if(lo == hi) return nums[lo];

       int mid = (hi - lo)/2 + lo;
       int left = majorityElementRec(nums,lo,mid);
       int right = majorityElementRec(nums,mid+1,hi);

       if(left == right) return left;

       // otherwise,合并提出最优的众数
       int leftCount = countInRange(nums,left,lo,hi);
       int rightCount = countInRange(nums,right,lo,hi);

       return leftCount > rightCount? leftCount:rightCount;

    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
}