
// leetcode 98
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

 // method 1
 // 递归
 // 不断验证上下界
 // 左子树全小于 node.val
 // 右子树全大于 node.val
 // 利用之前结果 return
 // 判断每个node 的上下界， 同时有上下界的限制
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return inorder(root, null, null);
    }
    public boolean inorder(TreeNode node, Integer lower, Integer upper){
        if(node == null) return true;
        if(lower != null && lower >= node.val) return false;
        if(upper != null && node.val >= upper) return false;
        if(!inorder(node.left, lower, node.val)) return false;
        if(!inorder(node.right, node.val, upper)) return false;
        return true;
    }   
}

// method 2
// 二叉搜索树的定义
// 实时检查中序遍历顺序下，是否升序
// 递归实现
// running time: O(N)
// class Solution {
//     private boolean flag;
//     private int cur = 0;
//     private int count = 0;
//     public boolean isValidBST(TreeNode root) {
//         flag = true;
//         inorder(root);
//         return flag;
//     }
//     public void inorder(TreeNode node){
//         if(!flag) return;
//         if(node == null) return;
//         inorder(node.left);
//         count++;
//         if( count > 1 && node.val <= cur){
//             flag = false;
//         }
//         cur = node.val;
//         inorder(node.right);
//     }
// }


// method 3
// stack 实现中序遍历, 先把左子树全都进栈，再处理
// running time: O(n)
// space: O(n)
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         Stack<TreeNode> stack = new Stack<TreeNode>();
//         double inorder = -Double.MAX_VALUE; 
//         while(!stack.isEmpty() || root != null){
//             while(root != null){
//                 stack.push(root);
//                 root = root.left;
//             }
//             root = stack.pop();
//             if(root.val <= inorder){
//                 return false;
//             }
//             inorder = root.val;
//             root = root.right;
//         }

//         return true;
//     }
// }


----------------------------------------------------------------------------------------------------------------------------------
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // 将有序数组转换成二叉树
 // 中序遍历
// method 1
// height-balanced binary tree
// 选择中间节点左边的节点作为根节点
// running time: O(n)
// space: O(lgn)
// class Solution {
//     public TreeNode sortedArrayToBST(int[] nums) {
//         return inorder(nums, 0, nums.length-1);
//     }

//     public TreeNode inorder(int[] nums, int left, int right){
//         if(left > right){
//             return null;
//         }
//         int mid = (left + right) / 2 ;
//         TreeNode node  = new TreeNode(nums[mid]);
//         node.left = inorder(nums, left, mid-1);
//         node.right = inorder(nums, mid+1, right);
//         return node;
//     }
// }



// method 2 
// 中序遍历， 选择中间右边节点作为根节点
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return inorder(nums, 0, nums.length-1);
    }

    public TreeNode inorder(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right + 1) / 2 ;
        TreeNode node  = new TreeNode(nums[mid]);
        node.left = inorder(nums, left, mid-1);
        node.right = inorder(nums, mid+1, right);
        return node;
    }
}


// method 3
// 任意中间节点
// class Solution {
//     Random rand = new Random();
//     public TreeNode sortedArrayToBST(int[] nums) {
//         return inorder(nums, 0, nums.length-1);
//     }

//     public TreeNode inorder(int[] nums, int left, int right){
//         if(left > right){
//             return null;
//         }
//         int mid = (left + right + rand.nextInt(2)) / 2 ;
//         TreeNode node  = new TreeNode(nums[mid]);
//         node.left = inorder(nums, left, mid-1);
//         node.right = inorder(nums, mid+1, right);
//         return node;
//     }
// }




---------------------------------------------------------------------------------------------------------------------------------------------
leetcode 1382 
// balanced-tree proof
// 贪心构造


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // AVL tree, balanced by rotation
 // 中序遍历, 有序数组
 // 然后有序数组还原
 // running time: O(n)
 // space: O(N)
class Solution {
    private List<TreeNode> treeNodes;
    public TreeNode balanceBST(TreeNode root) {
        treeNodes = new ArrayList<TreeNode>();
        inorder(root);
        return getBalancedTree(0, treeNodes.size()-1);
    }
    public void inorder(TreeNode node){
        if(node == null) return;
        inorder(node.left);
        treeNodes.add(node);
        inorder(node.right);
    }

    public TreeNode getBalancedTree(int left, int right){
        if(left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = treeNodes.get(mid);
        node.left = getBalancedTree(left, mid-1);
        node.right = getBalancedTree(mid+1, right);
        return node;
    }




}




































