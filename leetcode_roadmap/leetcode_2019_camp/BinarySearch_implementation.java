//二叉搜索
//核心：
//确定搜索的范围和区间
//取中间的数判断是否满足条件
//若不满足条件，判断应该往哪个半边继续进行搜索
//递归写法
int binarySearch(int[] nums, int target, int low, int high){
      if(low>hight){
          return -1;
      }
      //(high+low)/2:可能会溢出
      int middle  = low + (high-low)/2;
      if(nums[middle] == target){
         return middle;
      }
      //若目标数在左边，递归从左半边进行
      if(taget<nums[middle]){
         binarySearch(nums,target,low,middle-1);
      }
      else{
      	 binarySearch(nums,target,middle+1,high);
      }

}


//非递归写法
int binarySearch(int[] nums, int target, int low, int high){
     while(low<=hight){
        int middle = low + (high-low)/2;
        if(nums[middle]==target){
           return middle;
        }
        if(target<nums[middle]){
           high = middle - 1;
        }
        else{
           low = middle +1;
        }
     }
     return -1;

}