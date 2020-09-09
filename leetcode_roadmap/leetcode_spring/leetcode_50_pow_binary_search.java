// 快速幂 + 递归
// 递归分治的思想

// method 1
// brute force
// running time:O(n)
// n is signed integer
// TLE
/*
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        double result = 1.0;
        
        for(int i=0; i<Math.abs(n); i++){
            result = result * x;   
        }
        if(n < 0) result = 1.0 / result;
        return result;
    }
}
*/


// method 2:
// binary search 
// running time: O(logn)
// TLE
// compute from left to right
/*
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        if(n == 1 ) return x;
        if(n == -1) return 1.0/x;
        double result = 1.0;
        long cur = 0;
        long step = 1;
        double value_step = Math.abs(x);
        long upperBound = Math.abs((long) n);
        while(cur <= upperBound){
            if(cur == upperBound) break;
            
            if(cur + 2 * step <= upperBound){
                value_step = value_step * value_step;
                step = step * 2;
            }
            else{
                while(cur + step > upperBound){
                    value_step = Math.sqrt(value_step); 
                    step = step / 2;
                }
            }

            cur = cur + step;
            result = result * value_step;
        }
        
        if(n < 0) result = 1.0 / result;
        if(x<0 && upperBound % 2 == 1){
            result = -result;
        }
        
        return result;    
    }
}
*/

// sample answer
// 快速幂 + 递归
// 双向的思维思考
// 分治，递归的算法
// running time:O(logn)
/*
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N >=0? quickMul(x, N) : 1.0/quickMul(x,N);
    }
    
    public double quickMul(double x, long n){
        if(n == 0) return 1.0;
        
        // 向下取整，考虑奇偶数
        double y  = quickMul(x, n/2);
        return n%2 == 0? y * y : y * y * x; 
    }  
}
*/

// sample answer 2
// convert to iteration
// 幂数做整数的二进制拆分，就可以得到迭代计算的方法 
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N >=0 ? quickMul(x, N) : 1.0/quickMul(x,-N);
    }
    
    public double quickMul(double x, long n){
        double ans = 1.0;
        double x_contribution = x;
        
        while(n > 0){
            if(n % 2 == 1){
               ans = ans * x_contribution;
            }
            x_contribution = x_contribution * x_contribution;
            //舍弃 n 二进制表示的最低位，这样我们每次只要判断最低位即可
            n = n/2;
        }
        return ans;
    }  
}




























