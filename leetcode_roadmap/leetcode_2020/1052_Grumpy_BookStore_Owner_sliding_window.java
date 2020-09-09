// sliding window
// 滑窗，计算连续最大值
// 所有的not grumpy position and the maxvalue within a specified window
// running time: O(n)
/*
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int out = 0;
        int max = 0;
        // compute all element that must be satisfied because grumpy value is 0
        for(int i=0; i<customers.length; i++){
            if(grumpy[i] == 0) {
                out += customers[i]; 
                customers[i] = 0;
            } 
            // if set all that customers[i] to be 0, we ignore its interference
            // for concisement
        }
        // greedy, choose to use all sliding window
        int start = 0;
        int sum = 0;
        for(start=0; start+X-1< grumpy.length; start++ ){
            sum = 0;
            for(int i= start; i<start+X; i++){
                sum += customers[i];
            }
            if(sum > max) max = sum;
        }
        return max+out;
    }
}
*/


// method 2: general method, 伸缩或者扩展
// 同步操作
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int out = 0;
        int max = 0;
        // compute all element that must be satisfied because grumpy value is 0
        for(int i=0; i<customers.length; i++){
            if(grumpy[i] == 0) {
                out += customers[i]; 
                customers[i] = 0;
            } 
            // if set all that customers[i] to be 0, we ignore its interference
            // for concisement
        }
        // greedy, choose to use all sliding window
        int start = 0;
        int end = 0;
        int sum = 0;
        // when the window added up to X, increment start+1,and sum - customers[i] 
        // the sum from start to end currently which size is X-1 <= the sum from start to end with size = X
        // so we can add start and end together(simultaneously)
        for(end=0; end<grumpy.length; end++){
            sum += customers[end];
            if(end>=X){
                sum -= customers[end-X];
            }
            max = sum>max? sum:max;
        }
        return max + out;    
    }
}




















