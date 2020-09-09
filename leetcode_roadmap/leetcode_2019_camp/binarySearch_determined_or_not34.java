//二分查找+递归
//线性扫描：分别从左右开始扫描
//时间复杂度：O(N)
//空间复杂度：O（1）
//变型1：确定的边界
//确定边界：leetcode34
/*
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = searchLowerBound(nums,target,0,nums.length-1);
        result[1] = searchUpperBound(nums,target,0,nums.length-1);
        return result;
    }
    //下边界
    
    public int searchLowerBound(int[] nums, int target, int low, int high){
        if(low>high){
            return -1;
        }
        int middle = low + (high-low)/2;
        if(target==nums[middle]&&(middle==0||nums[middle-1]<target)){
            return middle;
        }
        if(target<=nums[middle]){
            return searchLowerBound(nums,target,low,middle-1);
        }
        else{
            return searchLowerBound(nums,target,middle+1,high);
        }
    }
    public int searchUpperBound(int[] nums,int target, int low, int high){
        if(low>high){
            return -1;
        }
        int middle = low + (high-low)/2;
        if(target==nums[middle]&&(middle==nums.length-1||nums[middle+1]>target)){
            return middle;
        }
        if(target<nums[middle]){
            return searchUpperBound(nums,target,low,middle-1);
        }
        else{
            return searchUpperBound(nums,target,middle+1,high);
        }
    }
    
}
*/
//非确定边界
//有序数组第一个大于6
//1:大于6
//middle==0 或者左边第一个数小于等于6
/*
int firstGreaterThan(int[] nums,int ){
    if(low>high) return -1;
    int middle = low+(high-low)/2;
    if(nums[middle]>target&&(middle==0)||middle[middle-1]<=target) return middle;
    if(target<nums[middle]){
         return firstGreaterThan(nums,target,low,middle-1);
    }
    else{
        return firstGreaterThan(nums,target,middle+1,high);
    }
}
*/

//不定长边界搜索
//寻找日志文件的长度，未记录文件记为null
//通过getUpperBound()函数不断去试探在什么位置
int getUpperBound(String[] logs,int high){
    if(logs[high]==null){
       return high;
    }
    return getUpperBound(logs,high*2);
}
//运用二分搜索方法寻找日志长度
int binarySearch(String[] logs, int low, int high){
   if(low>high){
      return -1;
   }
   int middle = low+(high-low)/2;

   if(logs[middle]==null&&logs[middle-1]!=null){
       return middle;
   }
   if(logs[middle]==null){
       return binarySearch(logs,low,middle-1);
   }else{
       return binarySearch(logs,middle+1,high);
   }
}























