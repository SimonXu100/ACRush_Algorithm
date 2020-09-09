//位置基准计算：防止越界
/*
class Solution {
    int[] nums;
    public int findKthLargest(int[] nums, int k) {
        //the position satisfying is nums.length-k
        this.nums = nums;
        return quickSelect(0,nums.length-1,nums.length-k);
    }
    public int quickSelect(int lo, int hi,int k_smallest){
    if(lo==hi){
       return this.nums[lo];
    }
    int p = partition(lo,hi); 
    if(p<k_smallest){
        //位置都进行了更新
        //参照一下算法，也可修改为，先结算当前数组大小，再进行判断
        return quickSelect(p+1,hi,k_smallest);
    }
    else if(p>k_smallest){
        return quickSelect(lo,p-1,k_smallest);
    }
    else{
        return this.nums[p];
    }  
}
   public int partition(int lo, int hi){
    Random random_num = new Random();
    //防止越界
    int pivot_index = lo + random_num.nextInt(hi - lo); 
    swap(pivot_index,hi);
    int i, j;
    //双指针
    //把基准点放到了数组末尾
    for(i = lo,j = lo;j<hi;j++){
       if(this.nums[j]<this.nums[hi]){
           swap(i++,j);
       }
    }
    //把基准点放在中间
    swap(i,j);
    return i; 
}
    public void swap(int i, int j ){
        int temp = 0;
        temp = nums[i];
        this.nums[i] = this.nums[j];
        this.nums[j] = temp;
    }
}
*/

//leetcode答案
//第K个大的值，直接求解，不进行转换
//随机取一个基准值，这里以最后一个数作为基准值
class Solution {
    int[] nums;
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0,nums.length-1,k);
    }
    public int quickSelect(int nums,int low, int high,int k){
       int pivot  = low;

       for(int j = low;j<nums.length;j++){
          if(nums[j]<=nums[high]){
             swap(nums,nums[pivot++],nums[j]);
          }
       }
       swap(nums,pivot,high);
       //pivot为第几大
       int count = high - pivot +1;
       if(count == k) return nums[pivot];
       //pivot太大，向左区间继续查找
       if(count<k){
           return quickSelect(nums,low,pivot-1,k-count);
       }
       else{
           return quickSelect(nums,pivot+1,high,k);     
       }
    }
}






