// accumulation function:
// List: list.clear()
// indegree degradation

//brute force: bfs from every note, record the tree with minimu height tree in result 

// BFS, Graph, topological, search from margin to center
// delete starting from the margin to center
// O(V+E)
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        // special case
        if(n == 1) {
            res.add(0);
            return res;
        }
        
        // build adjacent list
        // Map or directly list<list>
        int[] degree = new int[n];
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        for(int[] cur : edges){
            degree[cur[0]]++;
            degree[cur[1]]++;
            List<Integer> temp = adjList.getOrDefault(cur[0], new ArrayList<Integer>());
            temp.add(cur[1]);
            adjList.put(cur[0], temp);
            
            temp = adjList.getOrDefault(cur[1], new ArrayList<Integer>());
            temp.add(cur[0]);
            adjList.put(cur[1], temp);
        }
        
        // indegree 
        Queue<Integer> queue = new LinkedList<>();
        // add all node with 1 indegree to Queue
        for(int i=0; i<n; i++){
            if(degree[i] == 1) queue.offer(i);
        }
        // last layer of nodes is the final answer
        // update the result
        // method 1: create a new arrayList
        // method 2: every time clear
        int size = 0;
        int cur = 0;
        List<Integer> neighbors = null;
        while(!queue.isEmpty()){
            // method 1: using clear function
            size = queue.size();
            // res.clear();
            // method 2: create a new res List
            // more fast
            res =  new ArrayList<Integer>();
            // modified the indegree of all entry with its adjacent node
            for(int i=0; i<size; i++){
                cur = queue.poll();
                res.add(cur);
                neighbors = adjList.get(cur);
                for(int neighbor : neighbors){
                    degree[neighbor]--;
                    if(degree[neighbor] == 1 ) queue.offer(neighbor);
                }   
            }  
        }
        return res;
    }
}

// a tree is an undirected graph in which any two vertices are connected by exactly one path. 
// In other words, any connected graph without simple cycles is a tree




















