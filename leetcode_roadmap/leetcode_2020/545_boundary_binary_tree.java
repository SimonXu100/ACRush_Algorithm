// accumulation function


// meothod 1
// idea
// 左边界：我们沿左边遍历这棵树，不断向 resres 数组中添加节点，并保证当前节点不是叶子节点。如果位于某个节点，我们发现不存在左孩子，但存在右孩子，我们就将右孩子放入 resres 中并重复过程。下面的模拟描述了这个过程
// 

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
// dfs
// divide and conquer:
// left boudary+ leaf nodes + right boudary
// runnign time: 2 dfs,  1 for transversal all leaf nodes

// method 1
/*
class Solution {
    List<Integer> leaves;
    TreeNode root;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        this.root = root; 
        // more clear format, only require left or right boudary
        // preorder for left boudary and right boudary
    
        res.add(root.val);
        if(root.left != null)  stack.push(root.left);
        TreeNode tempNode = null;
        while(!stack.isEmpty()){
            tempNode = stack.pop();
            if(tempNode.left != null){
                stack.push(tempNode.left);
            }
            else{
                if(tempNode.right != null) stack.push(tempNode.right);
            }
            // keep the current node is not the leaves node
            if(tempNode.left != null || tempNode.right != null){
                res.add(tempNode.val);
            }
        }
        // add leaves node
        leaves = new ArrayList<Integer>();
        addLeaves(root);
        for(int value : leaves){
            res.add(value);
        }


        // find the right boudary
        if(root.right != null ){
            tempNode = root.right;
            while(true){
                if(tempNode.right != null ){
                    stack.push(tempNode);
                    tempNode = tempNode.right;
                }
                else{
                    if(tempNode.left != null){
                        stack.push(tempNode);
                        tempNode = tempNode.left;
                    }
                    else break;
                }
            }
        }
        // inverse adding
        while(!stack.isEmpty()){
            res.add(stack.pop().val);
        }
        return res; 
    }
    // preorder
    public void addLeaves(TreeNode node){
        if(node == null) return;
        if(node.left == null && node.right == null && node != root){
            leaves.add(node.val);
        }
        addLeaves(node.left);
        addLeaves(node.right);
    }
}
*/

// method 1: sample code
/*
public class Solution {

    public boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    public void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }

        }
        addLeaves(res, root);
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }
}
*/









// 先序遍历作为基础顺序
// 挑选上述结果的顶点，属于左边界或者叶子节点或者右边界上
// 根据之前结果，判断节点的类型
// method 2
// flag == 0: root
// flag == 1: left boundary
// flag == 2: right boundary
// flag == 3: 其他节点

// decide problem
// status pass

// important idea
/*
当前节点的左孩子，我们使用函数 leftChildFlag(node, flag)。当处理左孩子时，下面可能的情况，可以通过上图来区分：

当前节点是左边界节点：左孩子也是左边界节点。例如，图中 E 和 J 的关系。
当前节点是根节点：左孩子也是左边界节点。例如，图中 A 和 B 的关系。
当前节点是右边界节点：如果右孩子不存在那么左孩子就是右边界节点。例如，上图中的 G 和 N。但是如果右孩子存在，左孩子只能作为中间节点，如图中 C 和 F。
相似地，也会有 rightChildFlag(node, flag) 来判断当前节点的右孩子：

当前节点是右边界节点：右孩子也是右边界节点。例如，图中 C 和 G 的关系。
当前节点是根节点：右孩子也是右边界节点。例如，图中 A 和 C 的关系。
当前节点是左边界节点：如果左孩子不存在那么右孩子就是右边界节点。例如，上图中的 B 和 E。但是如果右孩子存在，左孩子只能作为中间节点
*/


class Solution {
    List<Integer> left_boundary;
    List<Integer> right_boundary;
    List<Integer> leaves;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        left_boundary = new ArrayList<Integer>();
        right_boundary = new ArrayList<Integer>();
        leaves = new ArrayList<Integer>();
        
        preorder(root, 0);
        res.addAll(left_boundary);
        res.addAll(leaves);
        res.addAll(right_boundary);

        return res; 
    }

    public void preorder(TreeNode node, int flag){
        if(node == null) return;
        // is_leaves
        if(node.left == null && node.right == null){
            leaves.add(node.val);
        }// is_left_boundary
        else if(flag == 1 || flag == 0){
            left_boundary.add(node.val);

        }// is_right_boundary
        else if(flag == 2){
            // add the node value inversely
            right_boundary.add(0, node.val);
        }

        preorder(node.left, flag_left_child(node, flag));
        preorder(node.right, flag_right_child(node, flag));
    }
    public int flag_left_child(TreeNode node, int flag){
        // flag: the node of parent
        // if the parent is left_boundary and this node is left_child
        // node: the parent node
        if(flag == 1 || flag == 0) return 1;
        else if(flag == 2 && node.right == null) return 2;
        else return 3; 

    }
    public int flag_right_child(TreeNode node, int flag){
        // flag: the node of parent
        if(flag == 2 || flag == 0) return 2;
        else if(flag == 1 && node.left == null) return 1;
        else return 3;
    }

}
















