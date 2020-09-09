// notes: binary tree: the usuage of recursive method
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
// longest path
// idea 0: brute force
// for binary tree, the bfs
// by this way, we should first convert it to a graph representation
// running time: O(n^2)




// dfs
// method 1
// running time: 
// 分类思想
// 最长路径经过其中一个节点
// Greedy: 常用方法计算一个节点的深度：max(depth of node.left, depth of node.right) + 1
// 二叉树常用递归 decision algorithm
// 常用方法计算一个节点的深度：max(depth of node.left, depth of node.right) + 1
// 在计算的同时，经过这个节点的路径长度为 1 + (depth of node.left) + (depth of node.right)
// 搜索每个节点并记录这些路径经过的点数最大值，期望长度是结果
// running time: O(N)
// space: O(N)
class Solution {
    int longest_path;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        // in this process, transversal all nodes already
        depthOf(root);
        return longest_path -1;
    }
    
    public int depthOf(TreeNode node){
        if(node == null) return 0;
        int depth_left = depthOf(node.left);
        int depth_right = depthOf(node.right);
        int depth = Math.max(depth_left, depth_right) + 1;
        if(depth_left + depth_right +1 > longest_path) longest_path = depth_left + depth_right +1;
        return depth;
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
























