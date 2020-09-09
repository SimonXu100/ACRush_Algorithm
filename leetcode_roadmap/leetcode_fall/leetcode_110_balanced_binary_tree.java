/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归
// track depth
// running time: O（n）
// 自顶向下
// 树的递归条件：
// 每一个节点都是平衡树
// 存在表达矛盾的话， 分拆函数
// class Solution {
//     public boolean isBalanced(TreeNode root) {
//         // 注意边界条件
//         if(root == null){
//             return true;
//         }
//         else{
//             return Math.abs(dfs_helper(root.left) - dfs_helper(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//         }
//     }

//     public int dfs_helper(TreeNode node){
//         if(node == null){
//             return 0;
//         }
//         int left_depth = dfs_helper(node.left);
//         int right_depth = dfs_helper(node.right);
//         return Math.max(left_depth, right_depth) + 1;
//     }
// }


// 自底向上
// 表示通过
class Solution {
    public boolean isBalanced(TreeNode root) {
        // 注意边界条件
        if(root == null){
            return true;
        }
        if(dfs_helper(root) == -1){
            return false;
        }
        else{
            return true;
        }
    }
    // 通过判断进行传递
    public int dfs_helper(TreeNode node){
        if(node == null){
            return 0;
        }
        int left_depth = dfs_helper(node.left);
        int right_depth = dfs_helper(node.right);

        if(left_depth == -1 || right_depth == -1 || Math.abs(left_depth - right_depth) > 1){
            return -1;
        }
        else{
           return Math.max(left_depth, right_depth) + 1; 
        }
    }
}

