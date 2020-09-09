// 环路问题
// 旅行问题
// 贪心算法
// method 0
// 检查每一个加油站， 模拟环路，看是否能够回到出发点
// running time: O(n^2)


// method 1
// 一次遍历的方法
// 如果有解，该答案为唯一答案
// 条件： sum(cost) < sum(gas)
// 如果加油站： gas[i] < cost[i], 不能从该处出发
// 如果在 i + 1 号加油站， curr_tank < 0 ，将 i + 1 号加油站作为新的起点，同时重置 curr_tank = 0 ，让油箱也清空
// 滑窗走查的方法： 前后关系相联系

// running time: O(n)
// space: O(1)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        int cur = 0;
        int start = 0;
        for(int i=0; i<n; i++){
            sum += gas[i] - cost[i];
            cur += gas[i] - cost[i];
            // if it does not arrive
            if(cur < 0){
                cur = 0;
                start = i + 1; 
            }
        }
        return sum >=0 ? start : -1;
    }
}




