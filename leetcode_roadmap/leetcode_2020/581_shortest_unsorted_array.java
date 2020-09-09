//[2,6,4,8,10,9,15]
//[1,2,3,3,3]
//[2,1]
//[5,4,3,2,1]
//[1,3,2,2,2]


// method optimal: running time: O(n)
// space:O(1)
//这个算法背后的思想是无序子数组中最小元素的正确位置可以决定左边界，最大元素的正确位置可以决定右边界
//我们再次遍历 numsnums 数组并通过与其他元素进行比较，来找到 minmin 和 maxmax 在原数组中的正确位置。我们只需要从头开始找到第一个大于 min 的元素，从尾开始找到第一个小于 max 的元素，它们之间就是最短无序子数组
// optimal solution
// two pass solution
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // the min value within subarray without order
        boolean flag = false;
        for(int i =1;i<nums.length;i++){
            //开始发生降序就要被统计在内
            if(nums[i]<nums[i-1]){
                flag = true;
            }
            if(flag) min = Math.min(nums[i],min); 
        }
        flag = false;
        for(int j =nums.length-1;j>0;j--){
            //开始发生升序就要被统计在内
            if(nums[j]<nums[j-1]){
                flag = true;
            }
            if(flag) max = Math.max(nums[j-1],max); 
        }
        // find the first element > min
        int l, r;
        for(l=0;l<nums.length;l++){
            if(nums[l]>min) break;
        }
        for(r=nums.length-1;r>=0;r--){
            if(nums[r]<max) break;
        }
        return r-l<0? 0:r-l+1;
    }
}

/*
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        
    }
}
*/
//2 sort, then compare
// running time: O(olgn)
// spacing: o(n)
// exceeding the time limits
/*
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        //if(nums.length==0 || nums.length==1) return 0;
        int[] nums_copy = nums.clone(); 
        Arrays.sort(nums_copy);
        for(int v: nums_copy){
            System.out.println(v);
        }
        int i =0;
        int j =nums.length-1;
        while(i<j){
            if(nums_copy[i]!=nums[i]&&nums_copy[j]!=nums[j]) break;
            if(nums_copy[i]==nums[i]) i++;
            if(nums_copy[j]==nums[j]) j--;
        }
        if(i==j) return j-i;
        if(i<j) return j-i+1;
        return 0;
    }
}
*/
//3 stack
// 最大点和最小点的位置， 一系列的比较
// 思想维护
// select sort idea
// notes: the empty stack problems
/*
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        //push into the position of element
        Stack<Integer> stack = new Stack<Integer>();
        // find the lowest bound of the subarray
        int i =0;
        stack.push(0);
        i++;
        int lowest_bound = Integer.MAX_VALUE;
        while(i<nums.length){
            if(!stack.isEmpty()){
                int top_index = stack.peek();
                if(nums[i]>=nums[top_index]){stack.push(i);}
                else{
                    while(nums[top_index]>nums[i]){
                        stack.pop();
                        if(!stack.isEmpty()){
                            top_index = stack.peek();
                        }
                        else{
                            lowest_bound = -1;
                            break;
                        }
                    }
                    lowest_bound = Math.min(lowest_bound,top_index);
                }
                i++;
            }
            else{
                lowest_bound = -1;
                break;
            }
        }
        Stack<Integer> stack2 = new Stack<Integer>();
        int j =nums.length-1;
        stack2.push(nums.length-1);
        j--;
        int highest_bound = Integer.MIN_VALUE;
        while(j>=0){
            if(!stack2.isEmpty()){
                int top_index2 = stack2.peek();
                if(nums[j]<=nums[top_index2]){stack2.push(j);}
                else{
                    //int pop_index = top_index2;
                    while(nums[top_index2]<nums[j]){
                        stack2.pop();
                        if(!stack2.isEmpty()){
                            top_index2 = stack2.peek();
                        }
                        else{
                            top_index2 = nums.length;
                            break;
                        }
                    }
                    highest_bound = Math.max(highest_bound,top_index2);
                }
                j--;
            }
            else{
                highest_bound = nums.length;
                break;
            }
        }
        return highest_bound - lowest_bound -1;      
    }
}
*/
// more simple version
/*
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack < Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
}
*/






























