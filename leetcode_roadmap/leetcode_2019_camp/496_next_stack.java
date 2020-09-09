// brute force
// find the position in the second array, and then search for the next greater number
// using hashtable
// running time: 0(n^2)
// metho 1 : hashtable 
/*
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        // build hashtable
        // element + position
        Map<Integer, Integer> hashmap = new HashMap<>();
        for(int i=0; i<n; i++){
            hashmap.put(nums2[i], i);
        }
        // find and search next greater element for every element in nums1
        int temp_position = 0;
        int temp = 0;
        for(int i=0; i<m; i++){
            // nums 1 is a subset of subset of nums2
            temp_position = hashmap.get(nums1[i]); 
            temp = nums1[i];
            nums1[i] = -1;
            for(int j= temp_position+1; j<n; j++){
                if(nums2[j] > temp) {
                    nums1[i] = nums2[j];
                    break;
                }
            } 
        }
        return nums1;  
    }
}
*/


// 单调栈
// method 2
// stack, sloved the adjacent relation between elements. Adopting proper condition, determing to represent this kind of relation
// using hashmap to store the intermediate result
// running time: O(n)
// space: O(n)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // build monotonic stack
        Stack<Integer> stack = new Stack<>();
        // create hashmap storing the intermediate result
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int[] res = new int[nums1.length];
        
        // maintain monotonic stack
        for(int i=0; i<nums2.length; i++){
            while(!stack.empty() && stack.peek() < nums2[i]){
                hashmap.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        // left elements cannnot find next greater element
        while(!stack.empty()){
            hashmap.put(stack.pop(), -1);
        }
        // output the result array
        for(int i=0; i<nums1.length; i++){
            res[i] = hashmap.get(nums1[i]);
        }
        return res;
    }
}




















