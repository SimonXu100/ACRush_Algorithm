//递归实现
//运行系统需要将当前程序中的变量和状态压入系统
//压入和引出栈需要较多时间

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
                   
    }
}


/*
[[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]
[0,4]
[4,4]

[[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]
[0,4]
[3,2]
*/
class Solution { 
    //定制相邻的元素增量
	int[] dx = {0,0,0,0};
	int[] dy = {0,0,0,0};
    Integer[] B = {0,0};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        B[0] = destination[0];
        B[1] = destination[1];
        return dfs(maze,start[0],start[1]);
    }
 	boolean dfs(int maze[][], int x, int y){
	   ///初始栈
	   Stack<Integer[]> stack = new Stack<>();
       //初始化压出stack,标记被访问过
       stack.push(new Integer[] {x,y});
       maze[x][y] = -1;

       //若不为空，不断循环
       while(!stack.isEmpty()){
          Integer[] pos = stack.pop();
          int m = pos[0];
          int n = pos[1];

          // 判断退出操作角度
          if(m == B[0] && n == B[1]){
            return true;
          }
          changeNext(maze,m,n);
          //压入邻近的未被访问的栈
          for(int d = 0; d<4; d++){
             int  i = dx[d];
             int  j = dy[d];

             if(isSafe(maze,i,j)){
               stack.push(new Integer[]{i,j});
               maze[i][j] = -1;
             }
          }
       }
       return false;
       
	}
	//定制isSafe()函数
	//越界与否
	//是否访问过
    //修改
	boolean isSafe(int[][] maze,int i, int j){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }
       if( maze[i][j] == -1){
           return false;    
       }
       return true;
	}
	//邻近点的判断：较复杂
    void changeNext(int[][] maze, int i, int j ){
        int temp = i;
        //注意条件书写正确
        //上方向边界
        while(temp>-1 && maze[temp][j] != 1){
            temp--;
        }
        dx[0] = temp+1;
        dy[0] = j;
        //下方边界
        temp  = i;
        while(temp<maze.length && maze[temp][j] != 1){
            temp++;
        }
        dx[1] = temp-1;
        dy[1] = j;
        //左边界
        temp  = j;
        while(temp>-1 && maze[i][temp] != 1){
            temp--;
        }
        dx[2] = i;
        dy[2] = temp+1;
        //右边界
        temp  = j;
        while(temp<maze[0].length && maze[i][temp] != 1){
            temp++;
        }
        dx[3] = i;
        dy[3] = temp-1;
    } 
}