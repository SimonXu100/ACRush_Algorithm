// backtracking algorithm
// key points: control the position of adding into backtracking
/*
import java.util.*;
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        backtracking_helper( res, nums, temp);
        return res;
    }
    public void backtracking_helper(List<List<Integer>> res,int[] nums, List<Integer> temp){
        int size = temp.size();
        if(temp.size()==nums.length){
            ArrayList<Integer> res_temp = new ArrayList<Integer>(temp);
            res.add(res_temp);
        }
        for(int i =0; i< nums.length-size;i++){
            temp.add(nums[i]);
            swap(nums,i,nums.length-size-1);
            backtracking_helper(res,nums,temp);
            swap(nums,i,nums.length-size-1);
            temp.remove(temp.size()-1);
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
*/

import java.util.*;
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int step=0;
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)  nums_lst.add(num);
        backtracking_helper( res, nums_lst, step);
        return res;
    }
    public void backtracking_helper(List<List<Integer>> res,ArrayList<Integer> nums, int step){
        if(step==nums.size()){
            ArrayList<Integer> temp = new ArrayList<Integer>(nums);
            res.add(temp);
        }
        for(int i =0; i< nums.size()-step;i++){
            Collections.swap(nums,i,nums.size()-step-1);
            backtracking_helper(res,nums,step+1);
            Collections.swap(nums,i,nums.size()-step-1);
        }
    }
}





