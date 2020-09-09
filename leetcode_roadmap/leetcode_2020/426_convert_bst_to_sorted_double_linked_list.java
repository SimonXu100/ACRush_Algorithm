/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
// circular double linkedlist
// method 1: inorder, and then build circular double linkedlist
// running time: O(n)
// space: O(n)
/*
class Solution {
    LinkedList<Node> list;
    public Node treeToDoublyList(Node root) {
        if(root == null ) return root;
        list = new LinkedList<Node>();
        // run inorder, get a sorted list
        inorder(root);
        // build the circular double linkedlist
        Node head = list.get(0);
        
        int size = list.size();
        for(int i=0; i<size-1; i++){
            list.get(i).right = list.get(i+1);
            list.get(i+1).left = list.get(i);
        }
        list.get(size-1).right = list.get(0);
        list.get(0).left = list.get(size-1);
        return head;
    }
    public void inorder(Node root){
        if(root == null) return;
        inorder(root.left);
        list.add(root);
        inorder(root.right);
    }
}
*/


// method 2:
// difference
// running time; O(n)
// space: recursive stack
class Solution {
    Node head;
    Node tail;
    public Node treeToDoublyList(Node root) {
        if(root == null ) return root;
        head = null;
        tail = null;
        inorder_helper(root);
        tail.right = head;
        head.left = tail;
        return head;   
    }

    public void inorder_helper(Node node){
        if(node == null) return;
        inorder_helper(node.left);
        if( tail == null){
            head = node;
        }
        else{
            tail.right = node;
            node.left = tail;
        }
        tail = node;
        inorder_helper(node.right);
    }  
}




















