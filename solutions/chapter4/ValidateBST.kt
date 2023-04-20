class ValidateBST {
    // time complexity: O(n), where n is the number of nodes in the tree
    // space complexity: O(log n) on a balanced tree, O(n) worst case
    fun isValidBST(root: TreeNode?): Boolean {
        return isBST(root, null, null)
    }

    private fun isBST(node: TreeNode?, min: Int?, max: Int?): Boolean {
        if (node == null) {
            return true
        }

        // check if current node value is within valid range
        if (min != null & & node.`val` <= min | | max != null & & node.`val` >= max) {
            return false
        }

        // check left and right subtrees recursively
        return isBST(node.left, min, node.`val`) & & isBST(node.right, node.`val`, max)
    }
}


class ValidateBSTTest {

    // example tree:
    // 4
    // /
    // 2   5
    // /
    // 1   3
    @ Test
    fun `valid BST`() {
        val root = TreeNode(4).apply {
            left = TreeNode(2).apply {
                left = TreeNode(1)
                right = TreeNode(3)
            }
            right = TreeNode(5)
        }
        val validator = ValidateBST()
        assertTrue(validator.isValidBST(root))
    }

    // example tree:
    // 2
    // /
    // 8   5
    // /
    // 1   3
    @ Test
    fun `invalid BST`() {
        val root = TreeNode(2).apply {
            left = TreeNode(8).apply {
                left = TreeNode(1)
                right = TreeNode(3)
            }
            right = TreeNode(5)
        }
        val validator = ValidateBST()
        assertFalse(validator.isValidBST(root))
    }

    // example tree:
    // null
    @ Test
    fun `empty BST`() {
        val validator = ValidateBST()
        assertTrue(validator.isValidBST(null))
    }
}
