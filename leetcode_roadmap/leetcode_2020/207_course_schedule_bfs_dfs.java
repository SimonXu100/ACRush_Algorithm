
// topological sort
// BFS + 入度
// 卡恩算法
// same method: check DAG
// 若没有build adjacent list, 需要遍历整个 list of edges
// 若没有build queue, 则同样需要遍历整个list of edges
// index: number mapping to courses
// method 1: do not build adjacent list, transverse all lists of edges
// 时间复杂度 O(N + M)O(N+M)，遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量
/*
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build indegree array
        int[] indegree =  new int[numCourses];
        int prev = 0;
        int cur = 0;
        for(int[] p : prerequisites){
            prev = p[1];
            cur = p[0];
            indegree[cur]++;
        }
        
        // bfs: indegree
        Queue<Integer> queue = new LinkedList<>();
        // initialization: add all original indegree 0 node
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0) queue.offer(i);
        }
        // decreasing indegree
        Integer temp_pre = 0;
        while(!queue.isEmpty()){
            temp_pre = queue.poll();
            numCourses--;
            for(int[] p : prerequisites){
                if(p[1] == temp_pre) {
                    indegree[p[0]]--;
                    if(indegree[p[0]] == 0) queue.offer(p[0]);
                } 
            }  
        }
        return numCourses == 0;  
    }
}
*/



// DFS
// topological relation
// idea got from analysis of algorithm
// adjacent matrix: 
// whether it is possible to find a topological sort == whether there is a DAG
// dfs: discovered duplicate nodes

// finised, discovery
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // flag status
        // build adjancey matrix
        int[][] adjacency  = new int[numCourses][numCourses];
        for(int[] p : prerequisites){
            adjacency[p[1]][p[0]] = 1;
        }
        // node flag
        int[] flags = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            if(!dfs(adjacency, flags, i)) return false;
        }
        return true;
  
    }
    public boolean dfs(int[][] adjacency, int[] flags, int i){
        // repeatedly access
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;
        // access currently
        flags[i] = 1;
    
        for(int j=0; j<adjacency[0].length; j++){
            if(adjacency[i][j] == 1 && !dfs(adjacency, flags, j)) return false;
        }
        // finished, out of DAG
        flags[i] = -1;
        return true;
    }
}




// method 2: by DFS, decide whether there exists DAG
/*
算法流程（思路是通过 DFS 判断图中是否有环）：
借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
未被 DFS 访问：i == 0；
已被其他节点启动的DFS访问：i == -1；
已被当前节点启动的DFS访问：i == 1。
对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 FalseFalse。DFS 流程；
终止条件：
当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 TrueTrue。
当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 22 次访问，即 课程安排图有环，直接返回 FalseFalse。
将当前访问节点 i 对应 flag[i] 置 11，即标记其被本轮 DFS 访问过；
递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 FalseFalse；
当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 -1−1 并返回 TrueTrue

作者：jyd
链接：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/




