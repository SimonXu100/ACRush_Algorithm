//递归
//分析：T:O(N)：访问所有节点
//S:O（N）: the worst case:都入栈
/*
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root,null,null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper){
       //判断输入状态是否违法
       //判断停止条件
       if(node == null){
           return true;
       }
       //非子树节点判断错误，返回
       if(lower!=null&&node.val<=lower) return false;
       if(upper!=null&&node.val>=upper) return false;
        
       //判断左右子树是否满足：左子树所有节点都小于当前节点，右子树所有节点都大于当前节点
       //缩小问题规模
       int val = node.val;
       if(!helper(node.left,lower,node.val)){return false;}
       if(!helper(node.right,node.val,upper)){return false;}
       return true;
    }
}
*/
//深度遍历
//深度优先非递归
//注意为判断左子树都小于当前节点
//同步栈
/*
class Solution {
    public boolean isValidBST(TreeNode root) {
        //需要额外空间存储上下限
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> lower_list = new Stack<Integer>();
        Stack<Integer> upper_list = new Stack<Integer>();
        stack.push(root);
        lower_list.push(null);
        upper_list.push(null);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            Integer lower = lower_list.pop();
            Integer upper = upper_list.pop();
            if(node == null) continue;
            if(lower!=null&&node.val<=lower){
                return false;
            }
            if(upper!=null&&node.val>=upper){
                return false;
            }
            
            stack.push(node.right);
            lower_list.push(node.val);
            upper_list.push(upper);
            
            stack.push(node.left);
            lower_list.push(lower);
            upper_list.push(node.val);
        }
        return true;
    }
}
*/
//中序遍历递归写法
//只比较节点之间值是不对的
//保证整体要有上下限之间的传递
//递归然后打印出排序结果，验证
//结果可能较慢
/*
class Solution {
    //注意数据范围
    long previous = Long.MIN_VALUE;
    boolean flag = true;
    public boolean isValidBST(TreeNode root) {
        inorder(root);    
        return flag;
    }
    
    public void inorder(TreeNode node){
         if(node==null){
             return;
         }
         inorder(node.left);
         if(previous>=node.val){
             flag = false;
             return;
         }
         previous = node.val;
         inorder(node.right);
         return;
    }
}
*/

/*
//中序遍历非递归写法
class Solution {
    //注意数据范围
    long previous = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {  
        return inorder(root);
    }
    public boolean inorder(TreeNode node){
        TreeNode cur = node;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty()||cur!=null){
            //先递归小循环所有左子树
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            //中序遍历打印过程
            cur = stack.pop();
            if(cur.val<=previous){
                return false;
            }
            previous = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
*/
