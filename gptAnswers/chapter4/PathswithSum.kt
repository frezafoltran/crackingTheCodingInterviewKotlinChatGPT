class PathsWithSum {
    fun countPathsWithSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null) return 0
        return countPath(root, targetSum) + countPathsWithSum(root.left, targetSum) + countPathsWithSum(root.right, targetSum)
    }

    private fun countPath(node: TreeNode?, targetSum: Int, runningSum: Int = 0): Int {
        if (node == null) return 0

        var paths = 0
        val currentSum = runningSum + node.`val`
        if (currentSum == targetSum) {
            paths++
        }
        paths += countPath(node.left, targetSum, currentSum)
        paths += countPath(node.right, targetSum, currentSum)
        return paths
    }
}

class PathsWithSumTest {

    private var pathsWithSum: PathsWithSum = PathsWithSum()

    private var root: TreeNode? = null

    init {
        /*
         * The tree:
         *        10
         *       /  \
         *      5   -3
         *     / \    \
         *    3   2   11
         *   / \   \
         *  3  -2   1
         *
         */
        root = TreeNode(10)
        root!!.left = TreeNode(5)
        root!!.left!!.left = TreeNode(3)
        root!!.left!!.left!!.left = TreeNode(3)
        root!!.left!!.left!!.right = TreeNode(-2)
        root!!.left!!.right = TreeNode(2)
        root!!.left!!.right!!.right = TreeNode(1)

        root!!.right = TreeNode(-3)
        root!!.right!!.right = TreeNode(11)
    }

    @Test
    fun `countPaths_8_Returns3`() {
        assertEquals(3, pathsWithSum.countPathsWithSum(root, 8))
    }

    @Test
    fun `countPaths_3_Returns3`() {
        assertEquals(3, pathsWithSum.countPathsWithSum(root, 3))
    }

    @Test
    fun `countPaths_7_Returns2`() {
        assertEquals(2, pathsWithSum.countPathsWithSum(root, 7))
    }

    @Test
    fun `countPaths_11_Returns2`() {
        assertEquals(2, pathsWithSum.countPathsWithSum(root, 11))
    }

    @Test
    fun `countPaths_10_Returns3`() {
        assertEquals(3, pathsWithSum.countPathsWithSum(root, 10))
    }

    @Test
    fun `countPaths_6_Returns0`() {
        assertEquals(0, pathsWithSum.countPathsWithSum(root, 6))
    }
}