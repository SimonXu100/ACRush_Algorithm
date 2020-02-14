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

// method 1: comparison of nodes
// preOrder transversal
// idea: first using preorder to access s, when meets the same node with t, then activate the preorder for t
// in this process, if one node do not match, then return false
// only focus on comparison of subtree
// running time: O(n*m), in general should be the number of Max(s,t)
// notes: the val may be duplicate, so try to test all possible node
/*
class Solution {
    TreeNode s;
    TreeNode t;
    boolean find;
    List<TreeNode> s_subroots;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        this.s = s;
        this.t = t;
        find = false;
        s_subroots = new ArrayList<TreeNode>();
        // find the matched nodes in s with t's root
        preorder(s);
        if(!find) return false;
        
        for(TreeNode s_subroot : s_subroots){
            if(preorder_comparison(s_subroot, t)) return true;
        }  
        return false;
    }
    
    public void preorder(TreeNode node){
        if(node == null) return;
        if(node.val == t.val){
            find = true;
            s_subroots.add(node);
        }
        preorder(node.left);
        preorder(node.right);     
    }
    
    public boolean preorder_comparison(TreeNode s_node, TreeNode t_node){
        if(s_node == null && t_node != null) return false;
        if(s_node != null && t_node == null) return false;
        if(s_node == null && t_node == null) return true;
         
        return (s_node.val == t_node.val)&& preorder_comparison(s_node.left, t_node.left) 
            && preorder_comparison(s_node.right, t_node.right);
    }  
}
*/

// method 1: optimization, combine the function
// return in advance if get a good answer
/*
class Solution {
    TreeNode s;
    TreeNode t;
    boolean find;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        this.s = s;
        this.t = t;
        find = false;
        // find the matched nodes in s with t's root
        preorder(s);
        if(find) return true;
        else return false;
    }
    
    public void preorder(TreeNode node){
        if(node == null) return;
        if(node.val == t.val){
            if(preorder_comparison(node, t)){
                find = true;
                return;
            }
        }
        preorder(node.left);
        preorder(node.right);     
    }
    
    public boolean preorder_comparison(TreeNode s_node, TreeNode t_node){
        if(s_node == null && t_node == null) return true;
        if(s_node == null || t_node == null) return false;
         
        return (s_node.val == t_node.val)&& preorder_comparison(s_node.left, t_node.left) 
            && preorder_comparison(s_node.right, t_node.right);
    }  
}
*/






// method 2
// comparison of two nodes: preorder

/*
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        
        
        
        
    }
}
*/

// running time:O(m^2 + n^2 + m*n)
// concatenation takes O(k) time for strings of length k and indexOf takes O(m*n)
// 先序遍历， 会先把子树遍历完成
// 标识特殊序列， 需要区分 lnull and rnull
// 并且考虑， 每个值前添加#， 区分字符串混合
/*
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree_s = preOrder(s,true);
        String tree_t = preOrder(t, true);
        return tree_s.indexOf(tree_t) >= 0;
        
    }
    
    public String preOrder(TreeNode node, boolean isLeft){
        if(node == null){
            if(isLeft) return "lnull";
            else return "rnull";
        }
        return "#"+node.val+preOrder(node.left,true)+preOrder(node.right,true); 
    }

}
*/
















// sample code
 /*
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s,t);
    }
    public boolean equals(TreeNode x,TreeNode y)
    {
        if(x==null && y==null)
            return true;
        if(x==null || y==null)
            return false;
        return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
    }
    public boolean traverse(TreeNode s,TreeNode t)
    {
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }
}
*/






// accumulation function
// String.indexOf() : return first index if found, otherwise return -1

















