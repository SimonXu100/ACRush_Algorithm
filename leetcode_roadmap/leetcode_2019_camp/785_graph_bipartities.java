//邻接表的方式给出
//深度优先或广度优先遍历----图
//基于图的遍历实现
//深度优先遍历
//递归实现
//graph[i]内无重复节点，内部没有自环
//dfs:图传统：需要额外的数据结构
//避免孤立节点存在，以每一个节点都操作一遍操作：遍历所有节点避免起始点（有孤立点不满足情况直接退出）
//颜色标记类别
//使用是否有类别，标记是否访问过
//深度优先遍历：上色加遍历的过程

//第一种判断方法
//后判断方法
        //当前步判断
        /*
        if(colors[node] != 0)
            return colors[node] == color;
        colors[node] = color;       
        for(int next : graph[node])
            if(!dfs(graph, -color, next))
                return false;
        return true;
        */
/*
class Solution {
    //全局变量减少传递参数
    int[] colors;
    public boolean isBipartite(int[][] graph) {
        //初始化类别：都为0，未被访问
        colors = new int[graph.length];
        //遍历所有节点，防止孤立点存在
        for(int i = 0;i<graph.length;i++){
            if(colors[i]==0&&!dfs(graph,1,i)){
                return false;
            }
        }
        return true;
    } 
    //递归传递值：需要操作的数据结构
    //判断步距的变量
    //由上部操作决定的值
    //发现不合理情况，快速返回
    //color当前上册
    boolean dfs(int[][] graph,int color, int node){
        //两种判断方法
        //第二种判断方法
        //isSafe的方法
        //提前判断是否访问
        colors[node] = color;
        for(int x : graph[node] ){
           if(colors[x] == color){
               return false;
           }
           if(colors[x] == 0&&!dfs(graph,-color,x)){
               return false;
           }            
        }
        return true;
    }
}*/
//bfs实现：
class Solution {
    //全局变量减少传递参数
    int[] colors;
    Queue<Integer> queue = new LinkedList<Integer>();
    public boolean isBipartite(int[][] graph) {
       int  n = graph.length;
       if(n==0||graph==null){
           return false;
       }
       colors = new int[n];
       queue = new LinkedList<Integer>();
       for(int i = 0;i<n;i++){
          if(colors[i]==0&&!bfs(graph,i,1)){
              return false;
          }
       }
       return true;
    } 
    public boolean bfs(int[][]graph,int i,int color){
        //先判断
        colors[i] = color;
        queue.add(i);
        while(!queue.isEmpty()){
            Integer pos = queue.poll();
            for(int x : graph[pos]){
                if(colors[x]==colors[pos]){
                    return false;
                }
                if(colors[x]==0){
                    colors[x] = -colors[pos];
                    queue.add(x);
                }
            }
        }
        return true;
    }
}
/**
        实际上就是一个上色问题, 依次判断每个节点(防止非连通图中有未访问的节点), 
        如果未被上色则选择一种颜色对其上色并dfs的对其邻接点上相反的颜色, 如果邻
        接点已有相同的颜色说明上色失败返回false, 0: 未上色, 1: 红色, 2: 黑色
        **/
//dfs: 非递归尝试