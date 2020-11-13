/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     private List<List<Integer>> res;
//     public List<List<Integer>> pathSum(TreeNode root, int sum) {
//         res = new ArrayList<List<Integer>>();
//         trackPath(root, sum, new ArrayList<Integer>());
//         return res;
//     }

//     public void trackPath(TreeNode node,  int target, List<Integer> tmp){
//         if(node == null) return;
//         tmp.add(node.val);
//         if(node.left == null && node.right == null && target == node.val){
//             res.add(new ArrayList<Integer>(tmp));
//         }
//         trackPath(node.left, target-node.val, tmp);
//         trackPath(node.right, target-node.val, tmp);
//         tmp.remove(tmp.size() - 1);
//     }
// }



// method 2
// bfs
// 路径追溯， Map
class Solution {
    private List<List<Integer>> res;
    private Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        queueNode.offer(root);
        // track distance
        queueSum.offer(0);
        while(!queueNode.isEmpty()){
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;
            if(node.left == null && node.right == null){
                if(rec == sum){
                    getPath(node);
                }
            }else{
                if(node.left != null){
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if(node.right != null){
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return res;
    }

    // 回溯path
    public void getPath(TreeNode node){
        List<Integer> tmp = new LinkedList<Integer>();
        while(node != null){
            tmp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(tmp);
        res.add(new LinkedList<Integer>(tmp));
    }




    
}




// 数据结构积累
// 队列
Deque<Integer> path = new LinkedList<Integer>();
path.offerLast(root.val);
path.pollLast();









































