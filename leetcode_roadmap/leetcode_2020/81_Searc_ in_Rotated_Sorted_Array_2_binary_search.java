// divide and conquer
// decision problems
// running time: O(lgn)

// method 1: first find the rotated index, and then run the commmon binary search in two parts
/*
class Solution {
    public int length;
    public int[] nums;
    public boolean search(int[] nums, int target) {
        this.length = nums.length;
        this.nums = nums;
        int left = 0;
        int right = this.length - 1;
        int mid  = 0;
        // validation check
        if(nums == null || nums.length ==0){
            return false;
        }
        if(nums.length == 1){
            if(nums[0] == target) return true;
            else return false;
        }
        
        int rotated_index = find_rotated_index(left,right);
        System.out.println(rotated_index);
        
        if(nums[rotated_index] == target){
            return true;
        }
        if(rotated_index == 0){
            left = 0;
            right = length -1; 
        }
        else{
            if(nums[left]>target) left = rotated_index;
            else right = rotated_index;
        }
        
        while(left<=right){
            mid = left + (right - left)/2;
            if(nums[mid] == target){
                return true;
            }
            else if(nums[mid] < target){
                left = mid + 1 ;
            }
            else{
                right = mid - 1;
            }
        }
        return false;
    }
    
    public int find_rotated_index(int left, int right){
        if (nums[left] < nums[right]) return 0;
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            
            //System.out.println(mid);
            if(nums[mid] > nums[mid+1]){
                return mid+1;
            }
            
            if(nums[mid] <= nums[left]){
                if(nums[left]<nums[right]) left = mid + 1;
                
                else{right = mid -1;}
            }
            else{
                left = mid + 1;
                
            }
             
        }
        return 0;
    }  
}
*/


// method 2: directly decide the dividing progress
// running time: worst case: 0(n)
// special case: the leftmost element is equal to rightmost element
class Solution {
    public boolean search(int[] nums, int target) {
        //validation check
        if(nums == null || nums.length ==0){
            return false;
        }
        int start = 0;
        int end = nums.length-1;
        int mid;
        
        // binary search
        
        while(start <= end){
            mid = start +  (end - start)/2;
            // condition 1: cannot decide which one is the sorted array from start to mid or from mid to end
            if(nums[mid] == target) return true;
            // eg: 10111, 11101
            if(nums[start] == nums[mid] ){
                // remove a duplicate term
                start++;
                continue;
            }
            // sorted in the first half part
            if(nums[start] < nums[mid]){
                if( nums[start] <= target && target < nums[mid]){
                    end = mid - 1;
                }
                else{
                    start = mid + 1;
                }
                
            }//sorted in the second half part
            else{
                if(nums[mid] < target && target <= nums[end]){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }  
            }            
        }
        return false;
     }      
}















































