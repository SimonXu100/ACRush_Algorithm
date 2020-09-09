// brute force: find all possible square: extension from every possible staring point as 1
// check if the new added elements have 0: if true: break, otherwise extent continually
// running time: O((mn)^2)
/*
class Solution {
    public int maximalSquare(char[][] matrix) {
        int max_length = 0;
        int rows = matrix.length;
        if(rows==0) return 0;
        int columns = matrix[0].length;
        int temp_length = 0;
        boolean flag = true;
        // extension from every possible starting points from 1
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                flag = true;
                if(matrix[i][j] == '1'){
                    temp_length = 1;
                    while(i+temp_length<rows && j+temp_length<columns){
                        // check if all the element in the current square are 1
                        int count = i;
                        while(count<=i+temp_length){
                            if(matrix[count][j+temp_length]=='0'){
                                flag = false;
                                break;
                            }
                            count++;
                        }
                        if(flag==false) break;
                        count = j;
                        while(count<=j+temp_length){
                            if(matrix[i+temp_length][count]=='0'){
                                flag = false;
                                break;
                            }
                            count++;
                        }
                        if(flag==false) break;
                        temp_length++;
                    }
                }
                max_length = temp_length > max_length? temp_length:max_length;    
            }
        }
        return max_length * max_length;
    }
}
*/


// DP: 2 dimension
// running time: O(mn)
// space: O(mn)
// dp[i][j] = min(dp[i-1][j-1], dp[i-1][j],dp[i][j-1]) + 1: if dp[i][j] = 1
/*
class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) return 0;
        int columns = matrix[0].length;
        int max_length = 0;
        // add the virtual boundary, eliminate the computing cost
        int[][] dp =  new int[rows+1][columns+1];
        for(int i=1;i<rows+1;i++){
            for(int j=1;j<columns+1;j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1]) +1;
                    max_length = Math.max(dp[i][j], max_length); 
                }
                
            }
        }
        return max_length * max_length;
    }
}
*/


// DP optimization: one dimension
// dp[i][j] = min(dp[i-1][j-1], dp[i-1][j],dp[i][j-1]) + 1: if dp[i][j] = 1
// running time: O(mn)
// space time:O(n)
// dp[j] = Min(dp[j],dp[j-1],prev): prev: dp[i-1][j-1]
// dp[j]: origianl dp[i-1][j]
// dp[j-1]: original dp[i][j-1]
class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) return 0;
        int columns = matrix[0].length;
        int max_length = 0;
        int prev = 0;
        int[] dp = new int[columns+1];
        for(int i=1;i<rows+1;i++){
            for(int j=1;j<columns+1;j++){
                int temp = dp[j];
                if(matrix[i-1][j-1] == '1'){
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    max_length = Math.max(dp[j], max_length); 
                }
                else{
                    dp[j] = 0;
                }
                prev = temp;
           }
        }
        return max_length * max_length;
    }
}






















