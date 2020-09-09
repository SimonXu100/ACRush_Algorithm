// bit manipulation experience accumulation


// bit manipulation
// method 1 
// java 补位标识
// 循环和位移动
// idea: 
// 1 遍历数字的 32 位。如果某一位是 11 ，将计数器加一
// 2 位掩码 来检查数字的第 ith digit
// 3 任何数字跟掩码 11 进行逻辑与运算，都可以让我们获得这个数字的最低位。检查下一位时，我们将掩码左移一位
// running: O(1)
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        // signed integer: 32
        for(int i=0;i<32;i++){
            if((n & mask) != 0){
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}


// method 2
// trick: bit manipulation
// optimization
// 不再检查数字的每一个位，而是不断把数字最后一个 1 反转
// idea: 关键的想法是对于任意数字 n ，将 n 和 n -1 做与运算，会把最后一个 1 的位变成 0
// 将 n 和 n - 1 与运算总是能把 nn 中最低位的 1 变成 0 ，并保持其他位不变
/*
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            n = n &(n-1);
            count++;
        }
        return count;
    }
}
*/





































