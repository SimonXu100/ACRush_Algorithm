// 排序（去重） + 双指针
// 使用两重循环分别枚举前两个数，然后在两重循环枚举到的数之后使用双指针枚举剩下的两个数。假设两重循环枚举到的前两个数分别位于下标 i 和 j，其中 i<j。初始时，左右指针分别指向下标 j+1 和下标 n−1。每次计算四个数的和，并进行如下操作
// 注意另外存在剪枝操作实现：
// 在确定第一个数之后，如果 \textit{nums}[i]+\textit{nums}[i+1]+\textit{nums}[i+2]+\textit{nums}[i+3]>\textit{target}nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target，说明此时剩下的三个数无论取什么值，四数之和一定大于 \textit{target}target，因此退出第一重循环；
// 在确定第一个数之后，如果 \textit{nums}[i]+\textit{nums}[n-3]+\textit{nums}[n-2]+\textit{nums}[n-1]<\textit{target}nums[i]+nums[n−3]+nums[n−2]+nums[n−1]<target，说明此时剩下的三个数无论取什么值，四数之和一定小于 \textit{target}target，因此第一重循环直接进入下一轮，枚举 \textit{nums}[i+1]nums[i+1]；
// 在确定前两个数之后，如果 \textit{nums}[i]+\textit{nums}[j]+\textit{nums}[j+1]+\textit{nums}[j+2]>\textit{target}nums[i]+nums[j]+nums[j+1]+nums[j+2]>target，说明此时剩下的两个数无论取什么值，四数之和一定大于 \textit{target}target，因此退出第二重循环；
// 在确定前两个数之后，如果 \textit{nums}[i]+\textit{nums}[j]+\textit{nums}[n-2]+\textit{nums}[n-1]<\textit{target}nums[i]+nums[j]+nums[n−2]+nums[n−1]<target，说明此时剩下的两个数无论取什么值，四数之和一定小于 \textit{target}target，因此第二重循环直接进入下一轮，枚举 \textit{nums}[j+1]nums[j+1]

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        // edge condition
        if(nums == null || nums.length < 4){
            return res;
        } 
        for(int i=0; i<nums.length-3; i++){
            // duplicate 
            if(i>0 && nums[i-1] == nums[i]) continue;
            // pruning condition
            if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) break;
            // almost largest cannnot found
            if(nums[i] + nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3] < target) continue;
            for(int j=i+1; j<nums.length-2; j++){
                // confirm the first two number
                // remove duplicate
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) break;
                if(nums[i] + nums[j] + nums[nums.length-2] + nums[nums.length-1] < target) continue;

                // then need to confirm the last two number
                int l = j + 1;
                int r = nums.length-1;
                while(l < r){
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if(sum < target){
                        l++;
                    }
                    else if(sum > target){
                        r--;
                    }
                    else{
                        // sum == target
                        // add tmp result
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        // remove duplicate
                        // 然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数
                        while(l < r && nums[l] == nums[l+1]) l++;
                        l++;
                        while(l < r && nums[r] == nums[r-1]) r--;
                        r--;
                    }
                }
            }

        }

        return res;
    }
}