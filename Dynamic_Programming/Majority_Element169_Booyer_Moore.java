// Boyer_Moore 投票算法
// 抹平差距，遗忘相同数量的众数和非众数
// sorting
// 分析：T(O（n）)
// 分析：S（O（n））
class Solution {
    public int majorityElement(int[] nums) {
       int count = 0;
       int majority = nums[0];
       for(int i = 0; i< nums.length; i++){
           if(count == 0) { majority = nums[i]; }
           if(nums[i] == majority){
               count++;
           }
           else{
               count--;
           }  
       }
      return majority;
    }
}
