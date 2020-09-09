/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// method 1
// dfs: searching rule
// house robber 3
// 两种选择根节点或者不选择根节点
// 动态规划：选中和不选中
// 树型动态规划
// memorization
// 状态转移方程
//  f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和， g(o)表示不选o 节点的子树节点的最大权值和
// f(o)=g(l)+g(r): 最大权值为左右子数中节点未被选中的子树最大权值相加
// 当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中
// g(o) = max{f(l),g(l)}+max{f(r),g(r)}
// map 存储中间结果： 
// iterative: 深度优先后续遍历
// running time: O(N)
// space: O(N)
// class Solution {
//     Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
//     Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
//     public int rob(TreeNode root) { 
//         dfs_postorder(root);
//         return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
//     }
//     public void dfs_postorder(TreeNode root){
//         if(root == null) return;
//         dfs_postorder(root.left);
//         dfs_postorder(root.right);
//         f.put(root, root.val + g.getOrDefault(root.left,0) + g.getOrDefault(root.right, 0));
//         g.put(root, Math.max(f.getOrDefault(root.left,0), g.getOrDefault(root.left, 0))+ Math.max(f.getOrDefault(root.right,0), g.getOrDefault(root.right, 0)));
//     }
// }




// method 2
// 只利用部分结果
// 优化
// running time: O(N)
// space: O(1)
class Solution {
    public int rob(TreeNode root) { 
        int[] tmp = dfs(root);
        return Math.max(tmp[0], tmp[1]);
    }
    
    public int[] dfs(TreeNode root){
        if(root == null) return new int[]{0,0};
        int[] l_subtree = dfs(root.left);
        int[] r_subtree = dfs(root.right);
        int selected = root.val + l_subtree[1] + r_subtree[1];
        int non_selected = Math.max(l_subtree[0], l_subtree[1]) + Math.max(r_subtree[0], r_subtree[1]);
        return new int[]{selected, non_selected};
    }
}




// 利用思路
// 另一种优化子结构
// max(爷爷节点 + 孙子节点, 儿子节点）










