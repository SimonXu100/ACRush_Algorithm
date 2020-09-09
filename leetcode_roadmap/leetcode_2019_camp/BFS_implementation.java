//BFS implementation 
//shortest path
//BFS: 一般用来解决最短路径问题：利用相同层的步距一致
//借用队列，需要判断是否访问过
//入队列尾的时标记：方式压入重复的节点
//出队列头时访问，打印

//此处迷宫矩阵访问，具体问题灵活访问
	int[] dx = {-1,0,1,0};
	int[] dy = {0,1,0,-1};
void bfs(int[][] maze, int x, int y){
   //step1: 创建队列，并将起点加入队列
	Queue<Integer[]> queue  = new LinkedList<>();
	queue.add(new Integer[]{x,y});
    //step3: 队列不为空，则一直循环
	while(!queue.isEmpty()){
        Integer[] pos = queue.poll();
        x = pos[0];
        y = pos[1];
        //step4: 四个方向上访问
        for(int d = 0;d<4;d++){
           int  i = x+dx[d];
           int j = y+dy[d];

           //step5:判断该方向是否已访问
           if(isSafe(maze,i,j)){
           	//step6:未被访问，记录步数，并加入队列
               maze[i][j] = maze[i][j]+1;
               queue.add(new Integer[]{i,j};
               if(i == B[0] && j == B[1]){
                   return;
               }
           }
        }
	}
 }
 //此处不同的值（代表步数），作为访问标志（>0）
boolean isSafe(int[][] maze,i,j){
       if(i<0||i>maze.length-1||j<0||j>maze[0].length-1){
           return false;
       }/*
       if(maze[i][j] == -1){
           return false;
       }
       if(maze[i][j] == 0){
       	   return false;
       }*/
       if(maze[i][j]<=0){
           return false;
       }
       return true;
	}
}
