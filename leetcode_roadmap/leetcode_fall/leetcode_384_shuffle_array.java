// 打乱一个没有重复元素的数组
// 洗牌问题，Fisher-Yates 洗牌算法即是通俗解法，同时也是渐进最优的解法

// method 1
// 根据排列规则排序
// 拿出数据的概率都是相同的
// 常用技巧 java: char list converted to char array
// running time: 
// O(n^2): 乘方时间复杂度来自于 list.remove（list.pop）
// import java.util.Arrays; 
// class Solution {
//     private int[] array;
//     private int[] original;
//     private Random rand = new Random();

//     public Solution(int[] nums) {
//         array = nums;
//         original = nums.clone();
//     }
    
//     /** Resets the array to its original configuration and return it. */
//     public int[] reset() {
//         array = original;
//         original = original.clone();
//         return array;
//     }
    
//     /** Returns a random shuffling of the array. */
//     // 打乱顺序， 使用 list
//     // 类似于掏口袋的方法
//     public int[] shuffle() {
//         List<Integer> aux = getArrayCopy();
//         for(int i=0; i<array.length; i++){
//             int removeIndex = rand.nextInt(aux.size());
//             array[i] = aux.get(removeIndex);
//             aux.remove(removeIndex);
//         }
//         return array;
//     }

//     private List<Integer> getArrayCopy(){
//         List<Integer> asList = new ArrayList<Integer>();
//         for(int i=0; i< array.length; i++){
//             asList.add(array[i]);
//         }
//         return asList;
//     }

// }


// method 2 
// Fisher-Yates 洗牌算法
// 让数组中的元素互相交换，这样就可以避免掉每次迭代中用于修改列表的时间了
// 此外还有一个需要注意的细节，当前元素是可以和它本身互相交换的 - 否则生成最后的排列组合的概率就不对了
// 在每次迭代中，生成一个范围在当前下标到数组末尾元素下标之间的随机整数。接下来，将当前元素和随机选出的下标所指的元素互相交换 - 这一步模拟了每次从 “帽子” 里面摸/// 一个元素的过程，其中选取下标范围的依据在于每个被摸出的元素都不可能再被摸出来了
// 关键点控制上下界
// running time: O(n): 交换是线性的
class Solution {
    private int[] array;
    private int[] original;
    private Random rand = new Random();

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }
    
    /** Returns a random shuffling of the array. */
  
    public int[] shuffle() {
        for(int i=0; i<array.length; i++){
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

    // offer 随机数的合理范围
    private int randRange(int min, int max){
        return rand.nextInt(max - min ) + min;
    }

    private void swapAt(int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

