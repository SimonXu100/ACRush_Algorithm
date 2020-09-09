//二分查找+分治算法
//剪枝策略
//分别比较中间值，剪值策略
/*
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        
        int k = (m+n)/2;
        //diverging based on the odd or even
        if((m+n)%2==1){
            return findKth(A,0,m-1,B,0,n-1,k+1);
        }
        else{
            return (findKth(A,0,m-1,B,0,n-1,k)+findKth(A,0,m-1,B,0,n-1,k+1))/2.0;
        }
    }
    //int k: 寻找第几小
    public double findKth(int[] nums1, int l1, int h1, int[] nums2, int l2, int h2, int k){
        int m = h1 - l1 + 1;
        int n = h2 - l2 + 1;
        //exchange in order to end more sooner
        if(m>n){
            return findKth(nums2,l2,h2,nums1,l1,h1,k);
        }
        // terminal condition
        if(m==0){
            return nums2[l2+k-1];
        }
        
        if(k==1){
            return Math.min(nums1[l1],nums2[l2]);
        }
        //median for both arrays
        //in case of overflow
        int na = Math.min(k/2,m);
        int nb = k-na;
        
        int va = nums1[l1+na-1];
        int vb = nums2[l2+nb-1];
        
        if(va==vb){
            return va;
        }
        else if(va<vb){
            return findKth(nums1,l1+na,h1,nums2,l2,l2+nb-1,k-na);
        }
        else{
            return findKth(nums1,l1,l1+na-1,nums2,l2+nb,h2,k-nb);
        }
    }
}
*/
//拓展1无序数组寻找第K大的数，参考215leetcode:quickSelect
//类似于quickSelect算法，但是在选择元素时，结合起来考虑
//默认partition设为末尾元素（即头一个元素）

class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        
        int k = (m+n)/2;
        //diverging based on the odd or even
        if((m+n)%2==1){
            return findKth(A,0,m-1,B,0,n-1,k+1);
        }
        else{
            return (findKth(A,0,m-1,B,0,n-1,k)+findKth(A,0,m-1,B,0,n-1,k+1))/2.0;
        }
    }
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


