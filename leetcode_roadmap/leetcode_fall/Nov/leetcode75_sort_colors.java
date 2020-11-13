// one-pass, using only o(1)
// without library's sort function
// 荷兰国旗问题


// 统计数量， 重写数组
// class Solution {
//     public void sortColors(int[] nums) {
//         int numRed = 0;
//         int numWhite = 0;
//         int numBlue = 0;
//         for(int num : nums){
//             if(num == 0) numRed++;
//             else if(num == 1) numWhite++;
//             else numBlue++;
//         }
//         for(int i=0; i<nums.length; i++){
//             if(numRed != 0){
//                 nums[i] = 0;
//                 numRed--;
//             }
//             else if(numWhite != 0){
//                 nums[i] = 1;
//                 numWhite--;
//             }
//             else{
//                 nums[i] = 2;
//                 numBlue--;
//             }
//         }
//     }
// }


// method 2
// 单指针, two pass
// space: o(1)
// running time: O(n)
// first swap all 0 in the front of the game
// then swap all 1 behind the 0s
// class Solution {
//     public void sortColors(int[] nums) {
//         int ptr = 0;
//         for(int i=0; i<nums.length; i++){
//             if(nums[i] == 0){
//                 swap(nums, ptr, i);
//                 ptr++;
//             }
//         }
//         // two pass for swap 1
//         for(int j=ptr; j<nums.length; j++){
//             if(nums[j] == 1){
//                 swap(nums, ptr, j);
//                 ptr++; 
//             }
//         }
//     }
//     public void swap(int[] nums, int i, int j){
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }



// one pass
// 双指针
// method 3
// class Solution {
//     public void sortColors(int[] nums) {
//         int ptr0 = 0;
//         int ptr1 = 0;
//         for(int i=0; i<nums.length; i++){
//             if(nums[i] == 0){
//                 swap(nums, ptr0, i);
//                 if(ptr0 < ptr1){
//                     // avoid mismatch with 1
//                     swap(nums, ptr1, i);
//                 }
//                 ptr0++;
//                 ptr1++;
//             }
//             else if(nums[i] == 1){
//                 swap(nums, ptr1, i);
//                 ptr1++;
//             }
//         }    
//     }
//     public void swap(int[] nums, int i, int j){
//         int temp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = temp;
//     }
// }









// method 4 
// one-pass
// 双指针，双侧, 从两端向中间
// 因此，当我们找到 2 时，我们需要不断地将其与 与 nums[ptr2] 与 2交换， 直到nums[i] != 0
class Solution {
    public void sortColors(int[] nums) {
       int ptr0 = 0;
       int ptr2 = nums.length-1;
       for(int i=0; i<=ptr2; i++){
           while(i<=ptr2 && nums[i] == 2){
               swap(nums, i, ptr2);
               ptr2--;
           }
           if(nums[i] == 0){
               swap(nums, ptr0, i);
               ptr0++;
           }
       }  
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}










