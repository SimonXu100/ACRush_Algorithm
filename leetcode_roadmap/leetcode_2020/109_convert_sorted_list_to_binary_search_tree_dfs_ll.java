/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// balanced binary tree
// Binary search tree
// need to keep balance 
// method 1: recursive fine the median 
// analysis: O(nlgn): T(n) = 2T(n/2) + o(n)
// space: recursive: o(lgn)
/*
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        TreeNode root = null;
        ListNode mid = find_median(head);
        // the treenode containing the midean node become the root of BST
        TreeNode node = new TreeNode(mid.val);
        // there just one element in the linked list
        if(head == mid){
            return node;
        }
        // recursively balanced bst
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }
    public ListNode find_median(ListNode head){
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // when slow is equal to head, cut down the two half
        if(prev != null){
            prev.next = null;
        }
        return slow;
    }
    
}
*/

// method 2
// change to array first
// runnign time: O(n): T(n) = 2T(n/2) + o(1)
// space: O(n)
/*
class Solution {
    private ArrayList<Integer> arr = new ArrayList<Integer>();


    public TreeNode sortedListToBST(ListNode head) {
        // mapping to LinkedList
        if(head == null) return null;
        this.arr = new ArrayList<Integer>();
        while(head!=null){
            this.arr.add(head.val);
            head = head.next;
        }
        return sortedListToBST_helper(0, this.arr.size()-1);
    }
    
    public TreeNode sortedListToBST_helper(int left, int right){
        if(left > right) return null;
        int mid = left + (right - left)/2;
        TreeNode node = new TreeNode(this.arr.get(mid));
        if(left == right){
            return node;  
        }
        node.left = sortedListToBST_helper (left, mid-1);
        node.right = sortedListToBST_helper(mid+1, right);
        return node;
    }
}
*/



// method 3
// inorder simulation
// running time: O(n)
// space: recursively stack: O(n)
class Solution {
    public ListNode p;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        this.p = head;
        ListNode cur  = head;
        // find the size
        int size = 0;
        while(cur!=null){
            size++;
            cur = cur.next;
        }
        //return null;
        return convert_BST(0, size-1);
    }
    
    public TreeNode convert_BST(int l, int r){
        if(l > r) return null;
        int mid = l + (r - l) / 2;
        // recursively from the left half
        TreeNode left = convert_BST(l, mid-1);
        
        // keep invariance: create the linked the TreeNode in sorted order
        TreeNode node = new TreeNode(this.p.val);
        node.left = left;
        this.p = this.p.next;
        
        // recursively from the right half
        TreeNode right = convert_BST(mid+1, r);
        node.right = right;
        return node;
    }
}























// BST: recursive substructure
// 一棵二叉搜索树是一棵有根二叉树并且对于所有节点满足特殊的性质：对于树中任意一个点，
//它的权值必然 \geq≥ 所有左子树节点的权值，\leq≤ 所有右子树节点的权值。因为二叉树具有递归的子结构，二叉搜索树也同理：所有子树也是二叉搜索树










// BST:

// 1 二叉树查找节点算法：
/*
若b是空树，则搜索失败，否则：
若x等于b的根节点的数据域之值，则查找成功；否则：
若x小于b的根节点的数据域之值，则搜索左子树；否则：查找右子树。
Status SearchBST(BiTree T, KeyType key, BiTree f, BiTree &p) {
    // 在根指针T所指二元查找樹中递归地查找其關键字等於key的數據元素，若查找成功，
    // 則指针p指向該數據元素節點，并返回TRUE，否則指针指向查找路徑上訪問的最後
    // 一個節點并返回FALSE，指针f指向T的雙親，其初始调用值為NULL
    if (!T) { // 查找不成功
        p = f;
        return false;
    } else if (key == T->data.key) { // 查找成功
        p = T;
        return true;
    } else if (key < T->data.key) // 在左子樹中繼續查找
        return SearchBST(T->lchild, key, T, p);
    else // 在右子樹中繼續查找
        return SearchBST(T->rchild, key, T, p);
}
*/

// 2 二叉树插入节点算法：
/*
向一个二叉搜索树b中插入一个节点s的算法，过程为：

若b是空树，则将s所指节点作为根节点插入，否则：
若s->data等于b的根节点的数据域之值，则返回，否则：
若s->data小于b的根节点的数据域之值，则把s所指节点插入到左子树中，否则：
把s所指节点插入到右子树中。（新插入节点总是叶子节点）
*/

// 3 删除节点
/*
在二叉查找树删去一个结点，分三种情况讨论：

若*p结点为叶子结点，即PL（左子树）和PR（右子树）均为空树。由于删去叶子结点不破坏整棵树的结构，则只需修改其双亲结点的指针即可。
若*p结点只有左子树PL或右子树PR，此时只要令PL或PR直接成为其双亲结点*f的左子树（当*p是左子树）或右子树（当*p是右子树）即可，作此修改也不破坏二叉查找树的特性。
若*p结点的左子树和右子树均不空。在删去*p之后，为保持其它元素之间的相对位置不变，可按中序遍历保持有序进行调整，可以有两种做法：其一是令*p的左子树为*f的左/右（依*p是*f的左子树还是右子树而定）子树，*s为*p左子树的最右下的结点，而*p的右子树为*s的右子树；其二是令*p的直接前驱（in-order predecessor）或直接后继（in-order successor）替代*p，然后再从二叉查找树中删去它的直接前驱（或直接后继）。
在二叉查找树上删除一个结点的算法如下
*/















