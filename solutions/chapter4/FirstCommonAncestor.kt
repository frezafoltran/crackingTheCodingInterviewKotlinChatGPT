class TreeNode < T > (val value: T, var left: TreeNode < T >?=null, var right: TreeNode < T >?=null)


fun < T > firstCommonAncestor(root: TreeNode < T >?, node1: TreeNode < T > , node2: TreeNode < T > ): TreeNode < T >? {
    if (root == null) return null
    if (root == node1 | | root == node2) return root

    val left = firstCommonAncestor(root.left, node1, node2)
    val right = firstCommonAncestor(root.right, node1, node2)

    return if (left != null & & right != null) {
        root
    } else if (left != null) {
        left
    } else {
        right
    }
}


class FirstCommonAncestorTest {

    @ Test
    fun `common ancestor exists`() {
//        / *
//        * 1
//        * /
//        * 2     3
//        * / /
//        *   4   5 6   7
//        * * /
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }

        val node1 = root.left!!.left!! // 4
        val node2 = root.right!!.left!! // 6

        assertEquals(root, firstCommonAncestor(root, node1, node2))
    }

    @ Test
    fun `one node is the ancestor of the other`() {
//        / *
//        * 1
//        * /
//        * 2     3
//        * / /
//        *   4   5 6   7
//        * * /
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }

        val node1 = root.left!! // 2
        val node2 = root.left!!.left!! // 4

        assertEquals(node1, firstCommonAncestor(root, node1, node2))
    }

    @ Test
    fun `common ancestor doesn't exist`() {
//        / *
//        * 1
//        * /
//        * 2     3
//        * / /
//        *   4   5 6   7
//        * * /
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
            right = TreeNode(3).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }

        val node1 = root.left!!.right!! // 5
        val node2 = root.right!!.left!! // 6

        assertNull(firstCommonAncestor(root, node1, node2))
    }
}
