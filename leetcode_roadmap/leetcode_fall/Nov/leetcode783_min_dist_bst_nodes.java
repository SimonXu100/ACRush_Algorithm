/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 暴力， 遍历比较： O(N^2)


 // 任意两个节点的值得差值最小
 // method 1
 // 遍历， 排序
 // running time: O(nlgn)
//  space: O(N)
// collections.sort()
class Solution {
    private List<Integer> list_node;
    public int minDiffInBST(TreeNode root) {
        int minDist = Integer.MAX_VALUE;
        list_node = new ArrayList<Integer>();
        dfs(root);
        // sort
        Collections.sort(list_node);
        if(list_node.size() == 1) return -1;
        for(int i=1; i<list_node.size(); i++){
            minDist = Math.min(minDist, list_node.get(i)-list_node.get(i-1));
        }
        return minDist;
    }
    // dfs 遍历所有node节点
    public void dfs(TreeNode node){
        if(node == null) return;
        dfs(node.left);
        list_node.add(node.val);
        dfs(node.right);
    }
    
}



// method 2
// 注意利用特殊性质， 二叉搜索树， 中序遍历有顺序
// 在遍历过程中，操作。 根据比较，track树
// running time: O(N)
// space: O(1): 不计算栈的大小
// 经验： 不能确认值， 使用Object == null 来实现
// class Solution {
//     private int minDist;
//     private int prev;
//     public int minDiffInBST(TreeNode root) {
//         minDist = Integer.MAX_VALUE;
//         prev = -1;
//         dfs(root);
//         return minDist;
//     }
//     public void dfs(TreeNode node){
//         if(node == null) return;
//         dfs(node.left);
//         if(prev != -1){
//             minDist = Math.min(minDist, node.val - prev);
//         }
//         prev = node.val;
//         dfs(node.right);
//     } 
// }























