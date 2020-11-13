// method 1
// hashtable 
// running time: O(n)
// space: O(n)
// class Solution {
//     public List<Integer> findDisappearedNumbers(int[] nums) {
//         Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
//         List<Integer> res = new ArrayList<Integer>();

//         // input all values
//         for(int key : nums){
//             hashmap.put(key, hashmap.getOrDefault(key,0)+1);
//         }

//         // check all Integer values from 1 to N
//         for(int i = 1; i<=nums.length; i++){
//             if(!hashmap.containsKey(i)){
//                 res.add(i);
//             }
//         }
//         return res;
//     }
// }

// method 2
// 输入和输出不算额外空间
// 原地修改数组
// 需要知道数组中存在的数字，由于数组的元素取值范围是 [1, N]，所以我们可以不使用额外的空间去解决它。
// 可以在输入数组本身以某种方式标记已访问过的数字，然后再找到缺失的数字

// 算法思想： 算法
// 遍历输入数组的每个元素一次。
// 我们将把 |nums[i]|-1 索引位置的元素标记为负数。即 nums[|nums[i] |- 1] \times -1nums[∣nums[i]∣−1]×−1 。
// 然后遍历数组，若当前数组元素 nums[i] 为负数，说明我们在数组中存在数字 i+1。
// 可以通过以下图片示例来帮助理解
// running time: O(n)
// space: O(n)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();

        for(int i=0; i<nums.length; i++){
            if(nums[Math.abs(nums[i])-1] > 0){
                nums[Math.abs(nums[i])-1] *= -1;
            }
        }

        // 重新遍历，找出有问题的数据
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                res.add(i+1);
            }
        }
        return res;
    }
}














