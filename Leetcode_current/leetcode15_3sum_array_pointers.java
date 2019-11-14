标签：数组遍历
首先对数组进行排序，排序后固定一个数 nums[i]nums[i]，再使用左右指针指向 nums[i]nums[i]后面的两端，数字分别为 nums[L]nums[L] 和 nums[R]nums[R]，计算三个数的和 sumsum 判断是否满足为 00，满足则添加进结果集
如果 nums[i]nums[i]大于 00，则三数之和必然无法等于 00，结束循环
如果 nums[i]nums[i] == nums[i-1]nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
当 sumsum == 00 时，nums[L]nums[L] == nums[L+1]nums[L+1] 则会导致结果重复，应该跳过，L++L++
当 sumsum == 00 时，nums[R]nums[R] == nums[R-1]nums[R−1] 则会导致结果重复，应该跳过，R--R−−
时间复杂度：O(n^2)O(n 
2
 )，nn 为数组长度


//sorted first
// for the purpose of unique property
// challenging points: duplicate issues
// firstly compute one, then compute other costs
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        ArrayList res = new ArrayList<ArrayList<Integer>>();
        int temp = 0;
        if(n < 3 || nums == null) return res;
        for(int i=0; i<n-2; i++ ){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i-1] == nums[i] ){
                continue;
            }    
            int l = i + 1;
            int r = n - 1;
            while(l < r){
                if(nums[l] + nums[r] > -nums[i] ){
                    r--;
                }
                else if(nums[l] + nums[r] < -nums[i] ){
                    l++;
                }
                else{
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while(nums[r-1] == nums[r]) r--;
                    while(nums[l] == nums[l+1]) l++;
                    l++;
                    r--;
                }     
            }       
        }
        return   res;
    }   
}


