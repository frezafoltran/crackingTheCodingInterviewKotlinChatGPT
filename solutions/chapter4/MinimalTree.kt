class MinimalTree {
    // Time complexity: O(n), where n is the size of the array
    // Space complexity: O(n)

    fun createMinimalBST(arr: IntArray): TreeNode? {
        return createMinimalBST(arr, 0, arr.size - 1)
    }

    private fun createMinimalBST(arr: IntArray, start: Int, end: Int): TreeNode? {
        if (start > end) {
            return null
        }
        val mid = (start + end) / 2
        val node = TreeNode(arr[mid])
        node.left = createMinimalBST(arr, start, mid - 1)
        node.right = createMinimalBST(arr, mid + 1, end)
        return node
    }
}


class MinimalTreeTest {

    @ Test
    fun `create minimal BST from sorted array`() {
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val expectedTree = TreeNode(5).apply {
            left = TreeNode(2).apply {
                left = TreeNode(1)
                right = TreeNode(3)
            }
            right = TreeNode(7).apply {
                left = TreeNode(6)
                right = TreeNode(8)
            }
        }

        val minimalTree = MinimalTree().createMinimalBST(arr)
        assertEquals(expectedTree, minimalTree)
    }
}
