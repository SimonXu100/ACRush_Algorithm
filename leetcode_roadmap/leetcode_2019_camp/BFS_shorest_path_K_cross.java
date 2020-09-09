//w:the largest wall that could be cutted through
//比较新颖的计量方法
	int[] dx = {-1,0,1,0};
	int[] dy = {0,1,0,-1};
int bfs(int[][] maze, int x, int y, int w){
	//step1:初始化变量，steps记录步数
	//z:记录level,用一个队列来存储要处理的各个层面的点
    int steps = 0;
    int z = 0;

    Queue<Integer[]> queue = new LinkedList<>();
    queue.add(new Integer[]{x,y,z});
    //小技巧，方便计算当前遍历了多少步，记录各层面的被访问情况
    //用null来隔离每一层
    queue.add(null);
    //每层独立访问
    boolean[][][] visited = new boolean[N][N][W+1]; 
    visited[x][y][x] = true;

    // step2: 去除当前节点，若到达目的地则返回步数
    while(!queue.isEmpty()){
       Integer[] pos = queue.poll();
       // 当前节点若不为null，则该层没有结束
       //因为先递增step,所以先判断
       if(pos!= null){
          x = pos[0];
          y = pos[1];
          z = pos[2];

          //判断是否到达终点
          if(x==B[0]&&y==B[1]){
             return steps;
          }
          //step4:若不是朝四个方向尝试下一步
          //根据题目实际制定
          for(int d = 0;d<4;d++){
             int i = x + dx[d];
             int j = y + dx[d];

             if(!isSafe(maze,i,j,z,visited)){
                 continue;
             }
             //step5:getLayer函数判断是否遇到可打透墙壁，是否到下一层
             //k:可能打透到下一层
             int k = getLayer(maze,w,i,j,z);
             if(k>=0){
                visited[i][j][k] = true;
                queue.add(new Integer[]{i,j,k});
             }
          }
       }//若当前为null,则表示处理完当前层，则继续下一层
       //上一层所有元素都已出队，下一层所有元素都已入队
       else{
           steps++;
           if(!queue.isEmpty()){
              queue.add(null);
           }
       }
    }
    //表示不存再改最短节点
    return -1;
}
// getLayer()
//思想:若当前遇到一堵墙，判断所打造墙壁个数是否超过规定；
//若没有，将其打通。否则返回-1
int getLayer(int[][] maze, int w, int x, int y, int z){
    if(maze[x][y]==-1){
       return z<w?z+1:-1;
    }
    return z;
}

//此处不同的值（代表步数），作为访问标志（>0）
boolean isSafe(int[][] maze,i,j,z,int[][][] visited){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }
       if(maze[i][j]==-1){
           return false;
       }
       if(visited[i][j][k] == true){
           return false;
       }
       return true;
	}
}












