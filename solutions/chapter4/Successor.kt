class BinaryTreeNode(val value: Int, var left: BinaryTreeNode?=null, var right: BinaryTreeNode?=null, var parent: BinaryTreeNode?=null)


class Successor {
    // time complexity: O(h), where h is the height of the tree
    // space complexity: O(1)
    fun findSuccessor(node: BinaryTreeNode): BinaryTreeNode? {
        // if node has a right child, the successor is the left-most node in the right subtree
        if (node.right != null) {
            var current = node.right
            while (current?.left != null) {
                current = current.left
            }
            return current
        } else {
            // if node does not have a right child, the successor is the lowest parent whose left child is also an ancestor of node
            var current = node.parent
            var child = node
            while (current != null & & current.right == child) {
                child = current
                current = current.parent
            }
            return current
        }
    }
}


class SuccessorTest {

    @ Test
    fun `find successor`() {
        val root = BinaryTreeNode(5)
        val left = BinaryTreeNode(1)
        val right = BinaryTreeNode(10)
        root.left = left
        root.right = right
        left.parent = root
        right.parent = root
        val leftRight = BinaryTreeNode(3)
        left.right = leftRight
        leftRight.parent = left
        val rightLeft = BinaryTreeNode(7)
        val rightRight = BinaryTreeNode(12)
        right.left = rightLeft
        right.right = rightRight
        rightLeft.parent = right
        rightRight.parent = right
        val successor = Successor().findSuccessor(root)
        assertEquals(7, successor?.value)
    }

    @ Test
    fun `no successor`() {
        val root = BinaryTreeNode(5)
        val left = BinaryTreeNode(1)
        val right = BinaryTreeNode(10)
        root.left = left
        root.right = right
        left.parent = root
        right.parent = root
        val leftRight = BinaryTreeNode(3)
        left.right = leftRight
        leftRight.parent = left
        val rightLeft = BinaryTreeNode(7)
        right.left = rightLeft
        rightLeft.parent = right
        val successor = Successor().findSuccessor(rightLeft)
        assertNull(successor)
    }
}
