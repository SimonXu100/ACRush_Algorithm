//递归的方法实现
class solution{
	//定制相邻的元素增量
	int[] dx = {-1,0,1,0};
	int[] dy = {0,1,0,-1};
 	boolean dfs(int maze[][], int x, int y){
	   //判断是否到达目的地
	   //也可以改成前置处理
       if(x==B[0] && y ==B[1]){
          return true;
       }
       //标记已经访问过
       //1： 直接修改数据（如下）：但是实际工作中容易产生意想不到的问题
       maze[x][y] = -1;

       //寻找相邻点入栈
       //根据实际情况课灵活定制

       for(int d = 0;d < 4;d++){
           int i  = x + dx[d];
           int j  = y + dy[d];

           if(isSafe(maze,i,j)&&dfs(maze,i,j)){
               return true;
           }
       } 
        return false;
	}

	//定制isSafe()函数
	//越界与否
	//是否访问过
	boolean isSafe(int[][] maze,i,j){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }
       if(maze[i][j] == -1){
           return false;
       }
       return true;
	}
}


//用非递归的方法实现
//栈的方法调用
class solution{
	//定制相邻的元素增量
	int[] dx = {-1,0,1,0};
	int[] dy = {0,1,0,-1};
 	boolean dfs(int maze[][], int x, int y){
 	   //初始栈
	   Stack<Integer[]> stack = new Stack<>();
       //初始化压出stack,标记被访问过
       stack.push(new Integer[] {x,y});
       maze[x][y] = -1;

       //若不为空，不断循环
       while(!stack.isEmpty()){
          Integer[] pos = stack.pop();
          int x = pos[0];
          int y = pos[1];

          // 判断退出操作角度
          if(x == B[0] && y == B[1]){
            return true;
          }

          //压入邻近的未被访问的栈
          for(int d = 0; d<4; d++){
             int  i = x + dx[d];
             int  j = y + dy[d];

             if(isSafe(maze,i,j)){
               stack.push(new Integer[]{i,j});
               maze[i][j] = -1;
             }
          }
       }
       return false;
    }

	boolean isSafe(int[][] maze,i,j){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }
       if(maze[i][j] == -1){
           return false;
       }
       return true;
	}
}































