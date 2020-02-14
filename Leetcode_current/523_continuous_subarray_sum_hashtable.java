// brute force idea
// continuous subarray: O(n^2)
// try every intermediate result
// method 0
/*
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        for(int i=0; i<nums.length;i++){
            sum = nums[i];
            for(int j=i+1; j<nums.length; j++){
                sum += nums[j];
                if(k == 0 && sum == 0) return true;
                if(k!= 0 && sum % k == 0) return true;
            }
        }
        return false;
    }
}
*/

// optimization, record the intermediate sum, and computer based on that number
// actually in this example, using addtional array, also need to check O(n^2) numbers of condition
// prefix sum array method


// HashMap + Math
// method 1
// 根据 modmod 运算的性质，我们知道 (sum[j] - sum[i])%k = sum[j]%k - sum[i]%k 
// 若想 (sum[j] - sum[i])%k = 0，则必有 sum[j]%k = sum[i]%k

//只要 sum%k 的值已经被放入 HashMap 中了，代表着有两个索引 i 和 j ，它们之间元素的和是 k 的整数倍。因此，只要 HashMap 中有相同的 sum\% ，我们就可以直接返回 true
// running time: O(n)
// space: O(min(n,k))

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> hashmap =  new HashMap<Integer, Integer>();
        hashmap.put(0,-1);
        int temp = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            
            if(k!=0) temp = sum % k;
            else temp = sum;
            if(hashmap.containsKey(temp)) {
                if(i - hashmap.get(temp) > 1) return true;
            } 
            else{
                hashmap.put(temp, i);
            }
               
        }
        return false;  
    }
}


























