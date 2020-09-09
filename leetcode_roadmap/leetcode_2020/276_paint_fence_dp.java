// idea: by recurrence, we could find the recurrence equation. But we use iteration write the program code
// 3 种思路

// Dynamic Programming
// transfer equation: N[j] = N[j-2] * (k-1 + k);
// (k-1): Color(j-1) == Color(j-2)
// (k) : Color(j-1) != Color(j-2)
/*
另一种思路就是当前颜色由前两栅栏的颜色是否相同决定
如果前两个栅栏的颜色相同，当前栅栏有 (k - 1) 种涂色方式
如果前两个栅栏的颜色不同，当前栅栏有 (k) 种涂色方式
*/
// method 1
class Solution {
    public int numWays(int n, int k) {
        if(n==0) return 0;
        if(n==1) return k; 
        int same = k;
        int diff = k * (k-1);
        int temp = diff;
        for(int i=2; i<n; i++){
            temp = diff;
            diff = (diff + same) * (k-1);
            same = temp;
        } 
        return diff + same;     
    }
}
// method 2 : dp and optimization
/*
和前一个颜色相同，此时说明前一个的栅栏的颜色应与更前面一个栅栏的颜色不同，更前一个栅栏的涂色方法有 F(n - 2) 种，前一个栅栏的涂色方式有 (k - 1) 种，
所以此时情况应为 F(n - 2) * (k - 1)。 
和前一个颜色不同，前一个栅栏的涂色方法有 F(n - 1) 种，当前栅栏的涂色方式有 (k - 1) 种，此时情况应为 F(n - 1) * (k - 1)
所以递推公式应为 F(n) = F(n - 2) * (k - 1) + F(n - 1) * (k - 1)
*/
// running time: O(n)
/*
class Solution {
    public int numWays(int n, int k) {
        if(n==0 || k==0) return 0;
        if(n==1) return k;
        int[] numbers = new int[n+1];
        numbers[1] = k;
        numbers[2] = k * k;
        //int result = k * k;
        for(int i=3; i<numbers.length; i++){
            numbers[i] = numbers[i-1]*(k-1) + numbers[i-2]*(k-1);
        }
        return numbers[n];
    }
}
*/



------------------------------------------------------------------------------------------------------------------------------------------------------
// 变型
//There are certain parts of a fence which need to be painted. Tom wants to divide work in such a way that each person gets exactly 1 contiguous part of the fence to paint.
// Test Cases:
n = 5, arr = 10101
Output = 4
The ways to divide the fence are: 
10|10|1
1|010|1
10|1|01 
1|01|01
n = 2, arr = 00 Output = 0
The fence does not need any painting


// 这道题不难，主要考虑全是0的情况
// When all the elements of the array are 0 then the result will be zero.
// Else, between two adjacent ones we must have only one separation. 
// So, answer equals to product of values posi + 1 – posi (for all valid pairs) where posi is the position of ith 1

// solution : slove the postion of all 1
// sum = sum *(position[i] - position[i-1]) for all 0<= i< p.length



 

















































