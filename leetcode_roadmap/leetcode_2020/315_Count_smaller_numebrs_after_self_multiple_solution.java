
// waiting for other optimization method

// brute force: 
// count the smaller numbers for every element
// O(n^2)

// how to keep the sorted order and as the same time


// method 1 binary search
/*
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        
        
        
    }
}
*/


// method 2: indexed array and merge sort
// 归并排序， 索引数组
// 求解 “逆序对” 的关键在于：当其中一个数字放进最终归并以后的有序数组中的时候，这个数字与之前看过的数字个数（或者是未看过的数字个数）可以直接统计出来，而不必一个一个数”
// 一个元素在算法的执行过程中位置发生变化，我们还想定位它，这样的场景我们在 “最小索引堆” 中曾经学习过
// “原始数组” 不变，用于比较两个元素的大小，真正位置变换的是 “索引数组”
// 为了完成 “索引数组” 的归并，我们还需要一个 “索引数组” 长度的临时数组，把索引数组的值复制过去，比较完成以后，再赋值回 “索引数组”

// 索引数组的优势： 再索引数组进行操作的时候， 我们能清楚的了解到这个元素在原始数组中的索引是多少， 进而把我们需要的逆序数的个数添加到结果数组中



// note:
/*
一、可以复习一下 “归并排序” 的细节。

1、如果 “前有序数组” 和 “后有序数组” 直接合并的时候，就有序，就不必归并；

2、在 “归并” 的时候，全局使用一个临时存储数组，而不必每一个归并都新建临时的存储空间。

二、出列一个元素的时候，马上得到右边比自己小的元素的个数，是通过不同的指针之间的距离得到的

三、如果你写过 “逆序数” 的计算的代码，你就会发现，“逆序数” 的计算可以在 “前有序数组” 元素出列的时候计算逆序数，也可以在 “后有序数组” 元素出列的时候计算逆序数，你可以比较一下它们在编码时候的不同之处。

*/

// sample code for 索引数组和归并拍戏
// 计算逆序对的方法
// 1 计算左边比当前数小的值： 后有序数组” 出列的时候计算逆序数

// 2 计算右边比当前小的值: 前有序数组 出列的时候计算逆序数
// condition 1: 当前有序数组和后有序数组都还有元素时，  当前有序数组中的元素数组出列，计算后续数组中已经出列的元素个数
// condition 2: 当前有序数组中的元素用完时，  后有序数组中的元素出列时不用计算逆序数。因为它不小于他前面的所有元素
// condition 3: 当后有序数组中的元素都用完时，  前有序数组中的元素出列时，  后有序数组的长度一下子成为了逆序数的个数，
// 因为后有序数组中的所有元素都不大于当前出列元素

class Solution {
    
    private int[] temp;
    private int[] counter;
    private int[] indexes;
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int len = nums.length;
        if(len == 0) return res;
        
        // initialization
        temp = new int[len];
        // maintain the original indexes
        indexes = new int[len];
        counter = new int[len];
        
        for(int i=0; i<len; i++){
            indexes[i] = i; 
        }
        mergeSort(nums, 0, len-1);
        // convert array to List
        for(int i : counter){
            res.add(i);
        }
        return res; 
    }
    
    public void mergeSort(int[] nums, int l, int r){
        // 数组只有一个元素， 没有比较， 不统计
        if(l==r) return;
        int mid = l + (r-l)/2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid+1, r);
        
        // optimization for merge sort
        // if indexes array is sorted, no need to continue
        if(nums[indexes[mid]] > nums[indexes[mid+1]]) {
            merge_count_smaller(nums, l, mid, r);
        }
    }
    // notes: nums[l...mid]: sorted
    // nums[mid+1 ... r]: sorted
    public void merge_count_smaller(int[] nums, int l, int mid, int r){
        // copy indexes array
        // only process part of range of array
        for(int i=l; i<=r; i++){
            temp[i] = indexes[i];
        }
        int i = l;
        int j = mid+1;
        // 左边出列的时候，计数
        for(int k=l; k<=r; k++){
            // 前有序元素已经完全出列，后有序数组出列后不用计算逆序数， 因为他不小于它前面的所有元素
            if(i>mid) {
                indexes[k] = temp[j];
                j++;
            }
            // 后有序元素已经完全出列，后面所有有序元素全小于当前左边数
            else if(j>r){
                indexes[k] = temp[i];
                i++;
                counter[indexes[k]] += (r - mid);  
            }
            else if(nums[temp[i]] <= nums[temp[j]]){
                indexes[k] = temp[i];
                i++;
                counter[indexes[k]] += (j - mid - 1);
                
            }
            // 防止重复计算， 找到边界再进行数字增加
            else{
                indexes[k] = temp[j];
                j++;
            }
        }
    }
   
}










// method 3 树状数组








// method 4 













