// method 1
// math
// 数学归纳法证明：
// 当N 为奇数， Alice 必败， 当N 为偶数， Alice 必胜
// running time: O(1)
class Solution {
    public boolean divisorGame(int N) {
        return (N % 2 == 0); 
    }
}



// method 2
// 递归的方法
// memorization
// enumeration
// dp[i]: when N == i, the status of people with first move
// running time: O(n^2)
// space: O(n)
// class Solution {
//     public boolean divisorGame(int N) {
//         boolean [] dp = new boolean[N+1];
//         if(N == 1) return false; 
//         // initalization
//         dp[1] = false;
//         dp[2] = true;
        
//         for(int i = 3; i <= N; i++ ){
//             // enumeration for victory or not
//             for(int j=i-1; j>0; j--){
//                 if(i % j == 0 && !dp[i-j] ){
//                     dp[i] = true;
//                     break;
//                 }
//             }
//         }
//         return dp[N];
//     }
// }




































