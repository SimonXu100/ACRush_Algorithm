// 要求： 空间复杂度为 O(1)
// 时间复杂度 O(n)
// 突破点为值的范围
// 标志是否出现过： 正负号表示
// 出现一次标定为负， 出现两次标定为正好
// 两次pass
// 区别没出现过的值
// mod 标志， 类似于赛列表
// running time: 
// class Solution {
//     public List<Integer> findDuplicates(int[] nums) {
//         List<Integer> res = new ArrayList<Integer>();
//         int n = nums.length;
//         for(int i=0; i<n; i++){
//             nums[((nums[i]-1) % n)] += n;
//         }
//         for(int i=0; i<n; i++){
//             if(nums[i] >= 2*n+1){
//                 res.add(i+1);
//             }
//         }
//         return res;
//     }
// }

// one pass
// track 上一步的结果
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n = nums.length;
        for(int i=0; i<n; i++){
            if(nums[Math.abs(nums[i])-1] < 0){
                res.add(Math.abs(nums[i]));
            }else{
                nums[Math.abs(nums[i])-1] *= -1;
            }
        }
        return res;
    }
}














