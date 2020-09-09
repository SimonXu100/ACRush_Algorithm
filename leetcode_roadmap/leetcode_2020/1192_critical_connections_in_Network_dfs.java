// accumulation information

// Kosaraju算法
// Kosaraju算法（也被称为Kosaraju–Sharir算法）是一个在线性时间内寻找一个有向图中的强连通分量的算法
//对有向图{\displaystyle G}G取逆，得到{\displaystyle G}G的反向图{\displaystyle G^{R}}{\displaystyle G^{R}}
//利用深度优先搜索求出{\displaystyle G^{R}}{\displaystyle G^{R}}的逆后排序
//对{\displaystyle G}G按照上述逆后排序的序列进行深度优先搜索
//同一个深度优先搜索递归子程序中访问的所有顶点都在同一个强连通分量内
//复杂度 分析：当图是使用邻接表形式组建的，Kosaraju算法需要对整张图进行了两次的完整的访问，每次访问与顶点数{\displaystyle V}V和边数{\displaystyle E}E之和{\displaystyle V+E}{\displaystyle V+E}成正比
//所以可以在线性时间{\displaystyle O(V+E)}{\displaystyle O(V+E)}内访问完成。该算法在实际操作中要比Tarjan算法和基于路径的强连通分量算法要慢，这两种算法都只需要对图进行一次完整的访问


// tarjan 算法
// 是一个在图中查找强连通分量的算法
// 每个节点只在一个强连通分量中出现，即使是在有些节点单独构成一个强连通分量的情况下（比如图中出现了树形结构或孤立节点）
//算法的基本思想如下：任选一节点开始进行深度优先搜索（若深度优先搜索结束后仍有未访问的节点，则再从中任选一点再次进行）。搜索过程中已访问的节点不再访问。搜索树的若干子树构成了图的强连通分量。
//节点按照被访问的顺序存入堆栈中。从搜索树的子树返回至一个节点时，检查该节点是否是某一强连通分量的根节点（见下）并将其从堆栈中删除。
//如果某节点是强连通分量的根，则在它之前出堆栈且还不属于其他强连通分量的节点构成了该节点所在的强连通分量。


// the basic idea:
// low link value of a node is the smallest(lowest) node id reachable from that node when doing a DFS(including itself)
// any node belong to a same low link value will belong to the same strongly connected components

// To cope with the random traversal order of the DFS. Tarjan algorithm maintains a set (often as a stack) of valid nodes from which to update low link values from
// Nodes are added to the stack of valid nodes as they are explored for the first time
// Nodes are removed from the stack each time a complete SCC is found.


// Tarjan Algorithm overview
// 1 mark the idea of each node as unvisited
// 2 Start DFS. Upon visting a node assign it an id and a low-link value. Also mark current nodes as visited and add them to a seen stack
// 3 On DFS callback, if the previous node is on the stack then min the current node's low link value with the last node's low link value
// 4 After visting all neighbors, if the curent node started a connected component( a node started a connected component if its id values equals its low link value)
// then pop nodes off stack until current node is reached.



// method 1: tarjan 算法
// here make some modification
// do not consider parent - node, mutual connection
// by this way, find the nodes that do not in the cycle, and even find the bridge between two cycle
// DFS
// brute force: random remove one of the connections, then run dfs check connectivity.
// if still mutually connected, then decide that is not critical connection
// otherwise, vice versa
// running time: O((V+E)*E)
/*
class Solution {
    Map<Integer, List<Integer>> adjList;
    boolean[] visited;
    int accessNumber;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // build adjacent List
        int length = connections.size();
        int temp1 = 0;
        int temp2 = 0;
        List<Integer> temp_lst = null;
        // initialization of hashmap
        adjList = new HashMap<Integer, List<Integer>>();
        for(List<Integer> lst:connections){
            temp1 = lst.get(0);
            temp2 = lst.get(1);
            temp_lst = adjList.getOrDefault(temp1, new ArrayList<Integer>());
            temp_lst.add(temp2);
            adjList.put(temp1, temp_lst);
            
            temp_lst = adjList.getOrDefault(temp2, new ArrayList<Integer>());
            temp_lst.add(temp1);
            adjList.put(temp2, temp_lst);   
        }
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // running selectedly remove one of connections and then run dfs
        for(int i=0; i<length; i++){
            visited = new boolean[n]; 
            accessNumber = 0;
            temp_lst = connections.get(i);
            // remove the related edge
            dfs(temp_lst.get(0), temp_lst);
            // check if all visited is true. if no, consider that edge as critical edge and add to res list
            if(accessNumber < n) res.add(connections.get(i));
        }
        return res;
    }
    
    public void dfs(int root, List<Integer> connection){
        accessNumber++;
        visited[root] = true;
        // check the adjacent node
        List<Integer> lst = adjList.get(root);
        for(int temp:lst){
            if(visited[temp] || (root == connection.get(0) && temp==connection.get(1))) continue;
            dfs(temp,connection);
        }
    }
    
}
*/



// method 2: faster DFS check method
// tarjan algorithm
// adjList stored sequentially
// this algorithm consider there are mostly 1 SCC, otherwise we need a for loop in outerspace
class Solution {
    // adjList
    Map<Integer, List<Integer>> adjList;
    int[] dfn;
    int[] low;
    boolean[] visited;
    List<List<Integer>> ret;
    // compute the number of 
    int times;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // initialization
        dfn = new int[n];
        low = new int[n];
        visited = new boolean[n];
        adjList = new HashMap<Integer, List<Integer>>();
        ret = new ArrayList<List<Integer>>();
        times = 0;
        
        // build List
        int temp1 = 0;
        int temp2 = 0;
        List<Integer> temp_lst = null;
        for(List<Integer> lst:connections){
            temp1 = lst.get(0);
            temp2 = lst.get(1);
            temp_lst = adjList.getOrDefault(temp1, new ArrayList<Integer>());
            temp_lst.add(temp2);
            adjList.put(temp1, temp_lst);
            
            temp_lst = adjList.getOrDefault(temp2, new ArrayList<Integer>());
            temp_lst.add(temp1);
            adjList.put(temp2, temp_lst);   
        }
        
        tarjan(0,-1);
        
        return ret;    
    }
    
    public void tarjan(int node, int parent){
        times++;
        // initialization: the link_value == index value
        dfn[node] = times;
        low[node] = times;
        visited[node] = true;
        
        for(int e: adjList.get(node)){
            if(e == parent) continue;
            
            if(!visited[e]){
                tarjan(e,node);
                // backtracking
                low[node] = Math.min(low[node], low[e]);
                
                if(low[e] > dfn[node]){
                    List<Integer> temp_lst = new ArrayList<Integer>();
                    temp_lst.add(node);
                    temp_lst.add(e);
                    ret.add(temp_lst);
                }
            }
            else{
                low[node] = Math.min(low[node], dfn[e]);
            }
        }
    }
}
















































// accumulation function
// c++
// vector<int>(n,0);
// n: 个数， 0: 初始值


// tarjan algorithm, 伪代码
/*
algorithm tarjan is
   input: 图 G = (V, E)
   output: 以所在的强连通分量划分的顶点集合
 
   index := 0
   S := empty // 初始化栈S为空栈
   for each v in V do
     if (v.index is undefined)
       strongconnect(v)
     end if
 
   function strongconnect(v)
     // 将未使用的最小index值作作为节点v的index
     v.index := index
     v.lowlink := index
     index := index + 1
     S.push(v)
 
     // 考虑节点v的后继节点
     for each (v, w) in E do
       if (w.index is undefined) then
         // 后继节点w未访问，递归调用
         strongconnect(w)
         v.lowlink := min(v.lowlink, w.lowlink)
       else if (w is in S) then
         // w已在栈S中，则其也在当前的强连通分量中
         v.lowlink := min(v.lowlink, w.index)
       end if
 
     // 若v是根则出栈，并得到一个强连通分量
     if (v.lowlink = v.index) then
       start a new strongly connected component
       repeat
         w := S.pop()
         add w to current strongly connected component
       until (w = v)
       output the current strongly connected component
     end if
   end function
*/




































// wrong answer: 
// check testing: 
// 6
// [[0,1],[1,2],[2,0],[1,3],[3,4],[4,5],[5,3]]
// method 2: check if on the cycle, any edge on the cycle is not critical edge
// check cycle using bfs+ indegree
// running time: O(V+E)
// indegree 
/*
class Solution {
    Map<Integer, List<Integer>> adjList;
    boolean[] visited;
    int[] degree;
    int accessNumber;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // build adjacent List
        int length = connections.size();
        int temp1 = 0;
        int temp2 = 0;
        
        List<Integer> temp_lst = null;
        // initialization of hashmap
        adjList = new HashMap<Integer, List<Integer>>();
        degree = new int[n];
        for(List<Integer> lst:connections){
            temp1 = lst.get(0);
            temp2 = lst.get(1);
            
            temp_lst = adjList.getOrDefault(temp1, new ArrayList<Integer>());
            temp_lst.add(temp2);
            adjList.put(temp1, temp_lst);
            degree[temp1]++;
            
            temp_lst = adjList.getOrDefault(temp2, new ArrayList<Integer>());
            temp_lst.add(temp1);
            adjList.put(temp2, temp_lst); 
            degree[temp2]++;
        }
        
        // check if there are cycle, record the removed edge util find the cycle
        // remove one edge on the cycle will not affect connectivity
        // otherwise, the edge that affect connectivity will be critical 
        // try it, but might be have bugs
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<n; i++){
            if(degree[i] == 1) queue.offer(i);
        }
        int temp =0;
        while(!queue.isEmpty()){
            temp = queue.poll();
            if(adjList.containsKey(temp)){
                temp_lst = adjList.get(temp);
                // add critical connections to res 
                for(int key: temp_lst){
                    degree[key]--;
                    List<Integer> lst = new ArrayList<Integer>();
                    lst.add(temp);
                    lst.add(key);
                    res.add(lst);
                    // check new node with degree == 1,and add to the queue
                    if(degree[key] == 1) queue.offer(key);
                }
            }
        }
        return res;
    }
}
*/



























