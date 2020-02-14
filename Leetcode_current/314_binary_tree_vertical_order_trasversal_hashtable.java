//test case:
[3,9,20,null,null,15,7]


[3,9,8,4,0,1,7]

[3,9,8,4,0,1,7,null,null,null,2,5]

// 通过211/212
// make pairs


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // method hashtable
 // record column
 // there exists bugs: 
 // method 1: 前序遍历确定col, 层序遍历确定
 // running time: O(n)
 // space: O(n)
 // original bugs: duplicate value
class Solution {
    // dfs: (value, lst of col with same value)
    Map<Integer, List<Integer>> hashmap;
    List<Integer> lst;
    Map<Integer, List<Integer>> col_map;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        hashmap = new HashMap<Integer, List<Integer>>();
        List<Integer> temp_lst = new ArrayList<>();
        col_map = new HashMap<Integer, List<Integer>>();
        List<Integer> temp_col = null;
        int temp = 0;
        trasverse(root);
        dfs_preOrder(root, 0);
        // avoid duplication
        // count, hashmap
       // Map<Integer, Integer> count_map = new HashMap<Integer,Integer>();
        for(int value : lst){
            // bug2: duplicated entries were added repeatedly
            /*
            count_map.put(value, count_map.getOrDefault(value, 0)+1);
            if(count_map.get(value) > 1) continue;
            */
            temp_col = hashmap.get(value);
            temp = temp_col.get(0);
            temp_col.remove(0);
            hashmap.put(value, temp_col);
            if(col_map.containsKey(temp)){
                temp_lst = col_map.get(temp);
                temp_lst.add(value);
                col_map.put(temp, temp_lst);
            }
            else{
                temp_lst = new ArrayList<Integer>();
                temp_lst.add(value);
                col_map.put(temp, temp_lst);
            } 
        }
        // sort the keySet and then add to res list
        temp_lst = new ArrayList<Integer>();
        for(int key : col_map.keySet()){
            temp_lst.add(key);
        }

        Collections.sort(temp_lst);
        // add to the res list
        for(int key : temp_lst){
            res.add(col_map.get(key));
        }
        return res;

    }

    // bfs, 访问顺序不对， 需要层序遍历, 一层一层的加坐标，然后再排序
    public void trasverse(TreeNode root){
        lst = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            lst.add(node.val);
            // add adjacent node
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    public void dfs_preOrder(TreeNode root, int col){
        if(root == null) return;
        if(hashmap.containsKey(root.val)) {
            List<Integer> temp_lst = hashmap.get(root.val);
            temp_lst.add(col);
            hashmap.put(root.val, temp_lst);
        }
        else{
            List<Integer> temp_lst = new ArrayList<Integer>();
            temp_lst.add(col);
            hashmap.put(root.val, temp_lst);
        }
        dfs_preOrder(root.left, col-1);
        dfs_preOrder(root.right, col+1);
    }

}


// method 2: 同时考虑横纵坐标，然后全部进行排序
// 先收集坐标，后排序
/*
class Solution {

    Map<Integer, List<Integer>> hashmap;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        hashmap = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
    }
    // dfs, 访问顺序不对， 需要层序遍历
    // bfs
    public void transverse(TreeNode root, int col){


    }
    
}
*/

// method 3 如果有有序的哈希表，比较合适
/*
class Solution {

    Map<Integer, List<Integer>> hashmap;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        hashmap = new HashMap<Integer, List<Integer>>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        

    }
    // dfs, 访问顺序不对， 需要层序遍历
    // bfs
    public void transverse(TreeNode root, int col){


    }
    
}
*/





