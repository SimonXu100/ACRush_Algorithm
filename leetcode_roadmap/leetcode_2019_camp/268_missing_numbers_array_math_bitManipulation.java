// Array, Bit manipulation
// requirement running time: O(n)
// space: O(n) or O(1)
// 取决于使用何种选择算法

// method 1 sort
// running time : O(nlogn) + O(n) = O(nlgn)
// 没有缺少任何数字是不可能的， n distinct number,  must be n + 1 values
/*
class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        // sort: O(nlgn)
        Arrays.sort(nums);
        int n = nums.length;
        // notes: dislocation phenomenon
        // decide if consecutive
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){
                return i;
            }
        }
        return n;
    }
}
*/

// method 2: hash
// store all array elements, then searching if all n+1 distinct values have been added
// running time: O(n)
// space: O(n)
/*
class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        
        // initialization problems
        // only care about if exists, so choosing HashSet
        Set<Integer> numSet = new HashSet<Integer>();
        for(int temp : nums){
            numSet.add(temp);
        }
        
        // check if the number has been missed
        for(int i=0; i<nums.length+1; i++){
            if(!numSet.contains(i)) return i;
        }
        return -1;
    }
}
*/

// method 3: Bit manipulation
// consecutive number
// XOR: 满足结合律， 对一个数字进行完全相同的异或运算会得到原来的数， 因此可以通过异或运算找到缺失的数字
// 同时出现，两次的相同的数字的异或的结果为0
// do not forget nums_length index
// running time: O(n)
// space: O(1)
/*
class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int missing = nums.length;
        int temp = 0;
        for(int i=0; i<nums.length; i++){
            temp = nums[i] ^ i;
            missing = missing ^ temp;
        }
        return missing;
    }
}*/
// summary
//我们知道数组中有 nn 个数，并且缺失的数在 [0..n][0..n] 中。因此我们可以先得到 [0..n] 的异或值，再将结果对数组中的每一个数进行一次异或运算。未缺失的数在 [0..n]
//和数组中各出现一次，因此异或后得到 0。而缺失的数字只在 [0..n][0..n] 中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。


// method 4 
// Math
// right total - real total sum
// total sum could be got by 高斯求和公式
// running time: O(n)
// space: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int sum = 0;
        for(int temp : nums) sum += temp;
        return n*(n+1)/2 - sum;
    }
}


// hashSet
// hash.add()
// hash.contains()
