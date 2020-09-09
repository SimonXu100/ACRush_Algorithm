// Array
// method 0
// brute force, tranversal all possible continuous subarray
// running time: O(n^2)
// the value of k and elements could be negatives
// no need additional space
/*
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int length = nums.length;
        int count = 0;
        for(int i=0; i<length; i++){
            sum = 0;
            for(int j=i; j<length; j++){
                sum += nums[j];
                if(sum == k){
                    count++;
                }
            }
        }
        return count;   
    }
}
*/

// array store the continuous sum
// running time: O(n^2)
// space: O(n)
// could use the following equation: 
// sum[i] = sum[i - 1] + nums[i - 1];
/*
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            sums[i] = sums[i-1] + nums[i];
        }
        
        for(int i=0; i<nums.length; i++){
            for(int j=i; j<nums.length; j++){
                if(sums[j] - sums[i] + nums[i] == k) count++;
            }
        }
    
        return count;   
    }
}
*/


// Hashtable one pass
// running time: O(n)
// 使用了一个哈希表 mapmap，它用于存储所有可能的索引的累积总和以及相同累加和发生的次数。我们以以下形式存储数据
// 我们遍历数组nums并继续寻找累积总和。每当我们遇到一个新的和时，我们在hashmap中创建一个与该总和相对应的新条目。
// key idea:  build hashmap store the speical counted value


// 利用 hashtable 快速寻找某些特殊值得存在情况， 并计算次数
// special computing equation is: sumj - sumi = k
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        // additional values for confirming the values equal to K as a single continuous array
        hashmap.put(0,1);
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            if(hashmap.containsKey(sum - k)){
                count += hashmap.get(sum-k);
            }
            hashmap.put(sum, hashmap.getOrDefault(sum,0)+1);
        }
        
        return count;   
    }
}


















