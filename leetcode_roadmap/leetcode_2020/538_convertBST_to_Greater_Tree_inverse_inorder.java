/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// recursive methods
// 反序中序遍历
class Solution {
    private int large_sum;
    public TreeNode convertBST(TreeNode root) {
        this.large_sum = 0;
        inverse_in_order(root);
        return root;
    }
    // in order traversal
    
    public void inverse_in_order(TreeNode node){
        if(node == null) return;
        inverse_in_order(node.right);
        this.large_sum = this.large_sum + node.val;
        node.val = large_sum;
        inverse_in_order(node.left);
    }
}


//extension: implementaion using stack


//方法 3：反序中序 Morris 遍历 [Accepted]: Time:O(N), Space: O(1)



// method 2: in order trasversal, then add together and then rewrite into the final tree node
// running time: O(n)+ O(n^2)
// notes: the final variables
/*
class Solution {
    private int count;
    public TreeNode convertBST(TreeNode root) {
        this.count = 0;
        List<Integer> temp = new ArrayList<Integer>();
        traversal(root, temp);
        for(int i = 0;i<temp.size();i++){
            for(int j =i+1;j<temp.size();j++){
                temp.set(i, temp.get(i) + temp.get(j));
            }
        }
        traversal_rewrite(root,temp);
        return root;
    }
    // in order traversal
    public void traversal(TreeNode node, List<Integer> temp){
        if(node==null) return;
        traversal(node.left,temp);
        temp.add(node.val);
        traversal(node.right,temp);
    }
    public void traversal_rewrite(TreeNode node, List<Integer> temp){
        if(node==null) return;
        traversal_rewrite(node.left,temp);
        node.val = temp.get(this.count);
        this.count++;
        traversal_rewrite(node.right,temp);
    }
}
*/

