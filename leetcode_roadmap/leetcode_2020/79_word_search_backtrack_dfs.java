// backtracking
// back: whether it has been visited
// 偏移量数组
// eliminate the number of passing by using membership elements
//running time: O（(V+E)*V）
class Solution {
    public int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public char[][] board;
    public String word;
    public boolean[][] flag;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        int n = word.length();
        int num_rows = board.length;
        int num_columns = board[0].length;
        boolean ans = true;
        char start = word.charAt(0);
        for(int i=0;i<num_rows;i++){
            for(int j=0;j<num_columns;j++){
                if(board[i][j]==start){
                   flag = new boolean[num_rows][num_columns];
                   flag[i][j] = true;
                   ans = backtrack_helper(n,0,i,j);
                   if(ans == true) return true;
                   flag[i][j] = false;
                }
            }
        }
        return false;
    }
    
    public boolean backtrack_helper(int n, int cur, int x, int y){
        if(cur==n) return true; 
        char c = word.charAt(cur);
        if(cur==n-1 && c==board[x][y]) return true;
        if(c != board[x][y]) return false;
        int num_columns = board[0].length;
        int num_rows = board.length;
        // the same cell cannot be used more than once
        for(int i=0;i<4;i++){
            int x1 = x + direction[i][0];
            int y1 = y + direction[i][1];
            if(x1<0 || x1>=num_rows) continue;
            if(y1<0 || y1>= num_columns) continue;
            if(flag[x1][y1]==false){
                flag[x1][y1] = true;
                if(backtrack_helper(n,cur+1,x1,y1)){return true;}
                else{
                    flag[x1][y1] = false;
                }
            }
        }
        return false;
    }
}





// dfs