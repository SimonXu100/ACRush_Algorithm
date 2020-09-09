






















-----------------------------------------------------------------------------------------
// python， Golang 编码记录
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def myBuildTree(preorder_left, preorder_right, inorder_left, inorder_right):
            if preorder_left > preorder_right:
                return None
            # 前序遍历第一个节点是根节点
            preorder_root = preorder_left
            # 中序遍历定位根节点
            inorder_root = indexMap[preorder[preorder_root]]

            root = TreeNode(preorder[preorder_root])
            size_left_subtree = inorder_root - inorder_left
            root.left = myBuildTree(preorder_left+1, preorder_left+size_left_subtree, inorder_left, inorder_root-1 )
            root.right = myBuildTree(preorder_left+size_left_subtree+1, preorder_right, inorder_root+1, inorder_right)

            return root

        indexMap = {value:i for i, value in enumerate(inorder) }
        return myBuildTree(0, len(preorder)-1, 0, len(inorder)-1)






func buildTree(preorder []int, inorder []int) *TreeNode {
    if len(preorder) == 0 {
        return nil
    }
    root := &TreeNode{preorder[0], nil, nil}
    i := 0
    for ; i < len(inorder); i++ {
        if inorder[i] == preorder[0] {
            break
        }
    }
    root.Left = buildTree(preorder[1:len(inorder[:i])+1], inorder[:i])
    root.Right = buildTree(preorder[len(inorder[:i])+1:], inorder[i+1:])
    return root
}



