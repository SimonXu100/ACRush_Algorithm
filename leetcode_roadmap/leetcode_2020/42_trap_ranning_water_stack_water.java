//trap water
//two pointes
//from starting point and ending point
// the property from all around
// running time: O(n)

class Solution {
    public int trap(int[] height) {
       int n = height.length;
       if(n<=2) return 0;
       int l = 0;
       int r = n-1;
       int sum = 0;

       int leftmax = 0;
       int rightmax = 0;
       
       while(l<r){
           if(height[l]<height[r]){
               if(leftmax<=height[l]){
                   leftmax = height[l];
               }
               else{
                   sum = sum + leftmax - height[l];
               }
               l++;
           }
           else{
               if(rightmax<height[r]){
                   rightmax = height[r];
               }
               else{
                   sum = sum +  rightmax - height[r];
               }
               r--;
           }
       } 
       return sum;
    }
}

// method 2 dynamic programming
// maintain an array to store the intermediate leftmax and rightmax from i
// eliminate duplicate result value
//running time: O(n)
// space: O(n)
/*
class Solution {
    public int trap(int[] height) {
       int n = height.length;
       if(n<=2) return 0;
       int sum = 0;
      // dp: find the leftmax value based on dp
       int[] leftmax = new int[n];
       int[] rightmax = new int[n];
       leftmax[0] = height[0];
       for(int i = 1;i<n;i++){
           leftmax[i] = Math.max(leftmax[i-1],height[i]); 
       }
       rightmax[n-1] = height[n-1];
       for(int j=n-2;j>=0;j--){
           rightmax[j] = Math.max(height[j],rightmax[j+1]);
       }
    
       // find the sum
        for(int k=1;k<n-1;k++){
            sum = sum + Math.min(leftmax[k],rightmax[k]) - height[k];
        }
        return sum;
    }
}
*/

// method 3: stack: one pass
// storing index
// keep the index contains the max value
// maintain the local optimal
//running time: O(n)
// space: O(n)
/*
class Solution {
    public int trap(int[] height) {
       int n = height.length;
       if(n<=2) return 0;
       int sum = 0;
       Stack<Integer> stack = new Stack<Integer>();
       stack.push(0);
       for(int k=1;k<n;k++){
            while(!stack.isEmpty() && height[k] > height[stack.peek()] ){
                int top = stack.peek();
                stack.pop();
                if(stack.isEmpty()) break;
                int distance = k - stack.peek() - 1;
                int bounded_height = Math.min(height[stack.peek()], height[k]) - height[top];
                sum = sum + distance*bounded_height;
            }
            stack.push(k);     
       }
       return sum;
    }
}

*/

























