/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// dfs
// recursive
// brute-force: starting from every node, extending the path. if the path exceeds the max path, then record it
// running time: O(n)
// 在每一步都检查哪种选择更好：是继续当前路径或者以当前节点作为最高节点计算新的路径
// when choose the max path, minus path sum could be ignored
// space: O(lgn)
// divide and conquer, single extending
class Solution {
    int max_sum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if( root == null) return 0;
        max_gain(root);
        return max_sum;
    }
    public int max_gain(TreeNode node){
        if(node ==  null) return 0;
        // when choose the max path, minus path sum could be ignored
        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);
        
        int sum_newpath = left_gain + right_gain + node.val;
        
        max_sum = Math.max(sum_newpath, max_sum);
        // for recursion
        // return the max gain if continue the same path
        return node.val + Math.max(left_gain, right_gain);
    }
}




