// brute force: O(N^2)
// method 1: according to the number of 0, make decision
// worst case : O(n^2)


// method 2
// 应用DP思想，重复的部分存储
// except product = left product * right product
// running time: O(n)
// space: O(1): outarray does not count as extra space

class Solution {
    public int[] productExceptSelf(int[] nums) {
        // multiple all elements, get the total product
        int[] res = new int[nums.length];
        res[0] = 1;
        // left product
        for(int i=1; i< res.length; i++){
            res[i] = res[i-1] * nums[i-1];
        }
        
        // right product 
        // transpose style
        int temp =  1;
        for(int i=res.length-1; i>=0;i--){
            // left prodcut * right product
            res[i] = temp * res[i];
            // right product
            temp = temp * nums[i];
        }
        return res;
    }
}


/*
// sample answer
class Solution {
    public int[] productExceptSelf(int[] nums) {

        // The length of the input array 
        int length = nums.length;

        // Final answer array to be returned
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all 
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the 
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}
*/
























