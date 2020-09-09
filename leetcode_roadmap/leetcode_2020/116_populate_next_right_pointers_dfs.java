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
// method 1: recursive method
// perfect binary tree: every interior have two children and all leave nodes are in the same level
// analysis: running time: O(n)
// space: (O(n): recursive)
class Solution {
    // zipper algorithm: uppper, then lower
    public Node connect(Node root){
        if(root == null) return null;
        if(root.left != null && root.right != null) root.left.next =  root.right;
        if(root.next != null && root.right != null) root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }  
}

// Method 2:  BFS
// definite position
/*
class Solution {
    public Node connect(Node root) {
        root = root.left;
        while(root!=null){
            System.out.println(root.val);
            root = root.next;
        }
        return null;

        
    }
}
*/








