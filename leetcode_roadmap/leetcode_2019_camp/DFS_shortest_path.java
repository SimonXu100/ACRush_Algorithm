//A[2]: start poin, B[2]:end point
//maze[][]: 迷宫，可灵活构建
//结果中每一点值代表到达起点的最小距离
//不设置是否访问过了
void solve(int maze[][]){
   // step1: 除A之外的所有0都有MAX替换
   for(int i = 0; i<maze.length;i++){
      for(int j =0; j< maze[0].length;j++){
           if(maze[i][j] == 0&&!(i==A[0]&&j==A[1]){
               maze[i][j] = Integer.MAX_VALUE;
           }
      }
   }

   //step2: 对矩阵进行DFS遍历
   dfs(maze,A[0],A[1]);

   //step3:判断是否存在路径
   if(maze[B[0]][B[1]]<Integer.MAX_VALUE){
      print("Shortest path count is: "+maze[B[0][B[1]]);

   }
   else{
   	  print("Cannot find B!");
   }
}
//临近链表灵活指定
void dfs(int maze[][],int x, int y){
	//step1:判断是否到达目的地
    if(x==B[0]&&y==B[1]){
        return;
    }
    for(int d = 0; d<4;d++){
    	//step2: 临近方向进行尝试，可灵活制定
       int i = x+dx[d];
       int j = y+dy[d];
       //step3: 若是下一步值大于当前步+1，则更新，并沿该点继续下去
       //否则，不理会，并不再尝试该方向并继续下去
       if(isSafe(maze,i,j)&&maze[i][j]>maze[x][y]+1){
            maze[i][j] = maze[x][y] + 1;
            dfs(maze,i,j);
       }
    }
}
//valid verification: 灵活制定
//此处不再设置访问过了与否，-1表示强
boolean isSafe(int[][] maze,int i, int j){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }
       if( maze[i][j] == -1){
           return false;    
       }
       return true;
	}


