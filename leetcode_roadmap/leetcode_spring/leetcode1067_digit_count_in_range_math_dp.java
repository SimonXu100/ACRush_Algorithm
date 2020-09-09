// idea
// N ∈ [1, 10^i][1,10 i]，在它们的左数第二位（右数第 ii 位）中，任意的 X 都出现了 10^{i-1}10  i−1 次
// hard math problems
// description
// method 1: dynamic programming
/*
class Solution {
    public int digitsCount(int d, int low, int high) {
        

    }
}
*/









// method 2
// Math
// running time: O(lgn): base number is 10
// 数学归纳法
class Solution {
    public int digitsCount(int d, int low, int high) {
        
        return count(high,d) - count(low-1, d);
    }
    // compute from 1 to n
    public int count(int n, int d){
        int count = 0;
        // tracking current digit number
        int k = 0;
        for(int i=1; (k = n/i)!=0; i=i*10){
            // high digit number: 取第 i 位左边（高位）的数字
            int high = k / 10;

            if(d == 0){
                if(high != 0){
                    high--;
                }
                else{
                    break;
                }
            }
            // 基础值
            count += high * i;
            // 当前位的数据
            int cur = k % 10;
            if(cur > d){
                count = count + i;
            }
            else if(cur == d){
                // n - k*i 为低位的数字
                count += n - k * i + 1;
            }
        }
        return count;
    }



    
}








// sample problems:
// leetcode 233: 数组1的个数
// https://leetcode-cn.com/problems/number-of-digit-one/
