//非确定边界
//33旋转过的排序数组
//非确定边界
//增加判断是否排好序
//通过nums[low]<=nums[high]：判断何为排好序的数组
class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums,target,0,nums.length-1);
    }
    public int binarySearch(int[] nums, int target, int low, int high){
        if(low>high) return -1;
        int middle = low + (high-low)/2;
        if(nums[middle]==target) return middle;
        
        //判断排序数组和非排序数组
        if(nums[low]<=nums[middle]){
            if(nums[low]<=target&&target<nums[middle]){
                return binarySearch(nums,target,low,middle-1);
            }
            else return binarySearch(nums,target,middle+1,high);
        }
        else{
            if(nums[middle]<target&&target<=nums[high]){
                return binarySearch(nums,target,middle+1,high);
            }
            else return binarySearch(nums,target,low,middle-1);
        }
    }
}
