//deque
//sliding window 
//常用场景：实现长度动态变化的窗口或者连续区间
//入队的为下标，提示区间长度
class Solution {
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    public int[] maxSlidingWindow(int[] nums, int k) {
       if(nums.length * k == 0) return new int[0];
       if (k == 1) return nums; 
       int[] ans = new int[nums.length-k+1];
       deq.addLast(0);
       for(int i = 1;i < nums.length;i++){
           if(i - deq.getFirst()> k-1){
                     deq.removeFirst();
             }
           while(!deq.isEmpty()&&nums[deq.getLast()]<nums[i]){
                   deq.removeLast();
               }
           deq.addLast(i);
           if(i>=k-1){
               ans[i-k+1] = nums[deq.getFirst()];
           }
       }
       return ans;
    }
}