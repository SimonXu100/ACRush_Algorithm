/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// Tree
// transveral together
// need to link new node together
// recursion
// running time: O(N)
// space: O(N)
/*
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return preOrder(t1, t2);
    }
    public TreeNode preOrder(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null) return null;
        if(t1 != null && t2 == null) return t1;
        if(t1 == null && t2 != null){
            return t2;
        }
        if(t1 != null && t2 != null){
            t1.val = t1.val + t2.val;
        }
        t1.left = preOrder(t1.left, t2.left);
        t1.right = preOrder(t1.right, t2.right);
        return t1;
    }
}
*/


// recursion optimization, more concise

class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return preOrder(t1, t2);
    }
    public TreeNode preOrder(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        
        t1.val = t1.val + t2.val;
        
        t1.left = preOrder(t1.left, t2.left);
        t1.right = preOrder(t1.right, t2.right);
        return t1;
    }
}




// iterative
// stack
/*
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return iter_helper(t1, t2);
    }
    
    // comparison of two nodes
    public TreeNode iter_helper(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        // two values in every iterative process
        // store the nodes that still needed to be compared
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[] {t1, t2});
        TreeNode[] t= null;
        while(!stack.isEmpty()){
            t = stack.pop();
            if(t[0] == null || t[1] == null) continue;
            t[0].val = t[1].val + t[0].val;
            
            if(t[0].left == null)  t[0].left = t[1].left;
            else stack.push(new TreeNode[]{ t[0].left, t[1].left});
            
            
            if(t[0].right == null) t[0].right = t[1].right;
            else stack.push(new TreeNode[] {t[0].right, t[1].right});
        }
        return t1;
    }
  
}
*/































