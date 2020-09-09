/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/


// DFS + Hashmap
// using hashmap store intermediate results, storing accessed node
// running time: O(V+E)
/*
class Solution {
    public Node cloneGraph(Node node) {
        Map<Integer, Node> hashmap = new HashMap<>();
        return dfs_helper(node, hashmap);
    }
    public Node dfs_helper(Node node, Map<Integer, Node> hashmap){
        if(node == null) return null;
        if(hashmap.containsKey(node.val)) return hashmap.get(node.val);
        Node clone =  new Node(node.val, new ArrayList<>());
        hashmap.put(node.val, clone);
        // access the neignbor nodes
        for(Node n : node.neighbors) clone.neighbors.add(dfs_helper(n, hashmap));
        return clone;    
    }
}
*/


// BFS
// running analysis: O(N+E)
// find the neighbor, and create new clone node and add to the queue for next addition to neighborous
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Node, Node> hashmap = new HashMap<>();
        
        Node clone = new Node(node.val, new ArrayList<>());
        
        hashmap.put(node, clone);
        // bfs
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            for(Node n : tmp.neighbors){
                if(!hashmap.containsKey(n)){
                    hashmap.put(n, new Node(n.val, new ArrayList<>()));
                    queue.offer(n);
                } 
                hashmap.get(tmp).neighbors.add(hashmap.get(n));
            }
        }
        return clone;
    }
}






