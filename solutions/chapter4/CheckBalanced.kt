class TreeNode(val value: Int, var left: TreeNode?=null, var right: TreeNode?=null)


class CheckBalanced {
    // time complexity: O(n), where n is the number of nodes in the tree
    // space complexity: O(d), where d is the depth of the tree
    fun isBalanced(root: TreeNode?): Boolean {
        if (root == null) return true
        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)
        return (Math.abs(leftHeight - rightHeight) <= 1) & & isBalanced(root.left) & & isBalanced(root.right)
    }

    private fun getHeight(node: TreeNode?): Int {
        if (node == null) return -1
        return 1 + Math.max(getHeight(node.left), getHeight(node.right))
    }
}


class CheckBalancedTest {
    private val checkBalanced = CheckBalanced()

    @ Test
    fun `isBalanced returns true for balanced tree`() {
        val rootNode = TreeNode(1,
                                TreeNode(2,
                                         TreeNode(4), TreeNode(5)),
                                TreeNode(3,
                                         TreeNode(6), TreeNode(7)))
        assertTrue(checkBalanced.isBalanced(rootNode))
    }

    @ Test
    fun `isBalanced returns false for unbalanced tree`() {
        val rootNode = TreeNode(1,
                                TreeNode(2,
                                         TreeNode(4,
                                                  TreeNode(8), TreeNode(9)),
                                         TreeNode(5)),
                                TreeNode(3,
                                         TreeNode(6,
                                                  right=TreeNode(10)),
                                         TreeNode(7)))
        assertFalse(checkBalanced.isBalanced(rootNode))
    }

    @ Test
    fun `isBalanced returns true for tree with only root node`() {
        val rootNode = TreeNode(1)
        assertTrue(checkBalanced.isBalanced(rootNode))
    }

    @ Test
    fun `isBalanced returns true for tree with one leaf`() {
        val rootNode = TreeNode(1, right=TreeNode(2))
        assertTrue(checkBalanced.isBalanced(rootNode))
    }
}
