/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serialize_helper(root, "");
    }
    public String serialize_helper(TreeNode root, String str){
        if(root == null){
            str += "None,";
        }else{
            // 先序遍历
            str += str.valueOf(root.val) + ",";
            str = serialize_helper(root.left, str);
            str = serialize_helper(root.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String> (Arrays.asList(data_array));
        return deserialize_helper(data_list);
    }

    public TreeNode deserialize_helper(List<String> data_list){
        if(data_list.get(0).equals("None")){
            data_list.remove(0);
            return null;
        }
        // 生成新的node
        TreeNode root = new TreeNode(Integer.valueOf(data_list.get(0)));
        data_list.remove(0);
        // 先左子树内容，后右子树内容
        root.left = deserialize_helper(data_list);
        root.right = deserialize_helper(data_list);
        return root;
    }
}




// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));