// 数学规律位运算
// 递归条件： 偶数直接n/2, 而奇数挑选n+1, n-1 哪一个替换次数最小
// greedy
// running time: o(lgn^2)
class Solution {
    public int integerReplacement(int n) {
        return replace_helper(n);
    }
    // 注意类型转换
    public int replace_helper(long cur){
        if(cur == 1) return 0;
        if(cur % 2 == 0) return replace_helper(cur/2) + 1;
        else{
            int plus = replace_helper(cur+1);
            int minus = replace_helper(cur-1);
            return plus < minus?plus+1: minus+1;
        }
    }
}