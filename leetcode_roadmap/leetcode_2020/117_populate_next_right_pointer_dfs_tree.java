/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
// method 1: half recursive and half iterative
// similar to linkedlist
// running time: O(n)
/*
class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        // the previous node
        // as the transfer node
        Node prev = root;
        Node cur = prev;
        Node next = null;
        // decide if the current node has at least one child
        // so if found this node, if one of his child is null, another must not be null
        while(cur != null){
            
            while (cur != null && cur.left == null && cur.right == null) cur = cur.next;
            // find the flollwing one
            if(cur != null && cur.left != null && cur.right != null) cur.left.next =  cur.right;
            if(cur != null) next = cur.next;
            while( next != null && next.left == null && next.right == null) next = next.next;
            // link together
            if(next != null){
                if(cur.right != null){
                    if(next.left != null) cur.right.next = next.left;
                    else cur.right.next = next.right;
                 }
                else{
                    if(next.left != null) cur.left.next = next.left;
                    else cur.left.next = next.right;     
                }
            }
            cur = next;
        }
        // find the first node that has at least one child
        while(prev != null && prev.left == null && prev.right == null) prev = prev.next;
        if(prev != null && prev.left != null) connect(prev.left);
        else {
            if(prev != null) connect(prev.right);
        }
        return root;
    }
}
*/




// method 2: BFS
// linked together, same level
// running time: O(n)
// space: o(n+e)
/*
class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node prev = null;
            Node cur = null;
            for(int i=0; i<size; i++){
                cur = queue.poll();
                if(i>0) prev.next = cur;
                prev = cur;
                // process as the order of level
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }   
            }  
        }
        return root;   
    }
}
*/

// method 3: cursory method
// dummy point
// the node that does not have child could not be added
// running time: O(n)

class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        Node cur = root;
        while(cur != null){
            Node dummy = new Node();
            // copy constructure
            // same thing
            Node tail = dummy;
            
            // traverse the current level
            while(cur != null){
                if(cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if(cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;  
        }
       return root; 
    }
}


















