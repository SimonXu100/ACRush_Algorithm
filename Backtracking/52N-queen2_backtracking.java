//回溯算法：
class Solution {
    //final count--global variables
    int count;
    public int totalNQueens(int n) {
       count = 0;
       backtracking(n,0,new int[n]);
       return count;
    }
    
    public void backtracking(int n, int row, int[] columns){
      //row: every horizontal line
      //columns: every vertical line
      //columns[row]: define the number of column in row
      //用数值表示所在列，降纬方法，非常巧妙
      
      // terminal condition
      if(row == n){
          count++;
          return;
      }
      //possible solution for every step:this row
      for(int col = 0; col<n;col++){
          //push 尝试方式
          columns[row] = col;
          //recursion
          //注意递归条件--可能
          if(check(row,col,columns)){
              backtracking(n,row+1,columns);
          }
          // pop 退回方式, 设置不合理的值
          // columns中只会记录合理的值
          columns[row] = -1;
      }
        
    }
    // for succinct
    // sanity check
    public boolean check(int row, int col, int[] columns){
        for(int r = 0; r<row;r++){
            // 同列、对角线排除
            if(columns[r] == col || row - r == Math.abs(columns[r] - col) ){
                return false;
            }  
        }
        return true;
    }
}