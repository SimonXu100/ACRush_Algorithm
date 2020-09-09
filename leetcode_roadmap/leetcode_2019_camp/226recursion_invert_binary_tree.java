/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//核心：只要完成交换每个节点的孩子即可
//树的遍历有特别特点：isVisited,不用检测，有连通度直接控制
//树的遍历翻转：
//联系镜像树
//递归
//即每一层都调转一下位置，即完成整体二叉树的翻转调整
//时间复杂度分析：T：O(N)
//空间复杂度：最多时间存储O(h): h:树高：lgn
//the worst case:n:非常不平衡的树结构
/*
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left  = invertTree(root.left);
        
        root.right = left;
        root.left = right;
        return root;    
    
    }
}
*/
//迭代方法
//队列，广度优先搜索，控制入队的节点交换
//栈的方法写一致始的时候，只有根节点在这个队列里面。只要这个队列不空，就一直从队列中出队节点，然后互换这个节点的左右孩子节点，接着再把孩子节点入队到队列，对于其中的空节点不需要加入队列。最终队列一定会空，这时候所有节点的孩子节点都被互换过了，直接返回最初的根节点就可以了
/*
class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root==null){
            return null;
        }
        //初始化
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return root;
    }
}
*/
//栈

class Solution {
    public TreeNode invertTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root==null){
            return null;
        }
        //初始化
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
           
        }
        return root;
    }
}
