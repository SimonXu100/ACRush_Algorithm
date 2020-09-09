// method 1
// running time: O(n)
// with extra memory: hashtable
/*
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> count = new HashMap<Integer,Integer>();
        for(int value : nums){
            count.put(value, count.getOrDefault(value,0) + 1);
        }
        
        for(int key : count.keySet()){
            if(count.get(key) == 1){
                ans = key;
                break;
            }
        }
        return ans;       
    }
}
*/


// method 2:
// bit manipulation
// 异或运算
// 任何数和 0 做异或运算，结果仍然是原来的数
// 任何数和其自身做异或运算, 结果是 0
// 异或运算满足交换律和结合律
// running time: O(N)
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        
        for(int value : nums){
            ans ^= value;
        }
        return ans;
    }
}

















