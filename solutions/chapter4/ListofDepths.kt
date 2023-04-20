class TreeNode(val value: Int, var left: TreeNode?=null, var right: TreeNode?=null)


class ListNode(val value: Int, var next: ListNode?=null)


class ListOfDepths(private val root: TreeNode) {
    // time complexity: O(n), where n is the number of nodes in the tree
    // space complexity: O(n)

    fun createListsOfDepths(): List < ListNode > {
        val result = mutableListOf < ListNode > ()
        if (root == null) return result

        var currentLevelNodes = mutableListOf(root)
        while (currentLevelNodes.isNotEmpty()){
            val nextLevelNodes = mutableListOf < TreeNode > ()
            val currentLevelList = ListNode(-1)
            var temp = currentLevelList

            for (node in currentLevelNodes){
                temp.next = ListNode(node.value)
                temp = temp.next!!

                node.left?.let {nextLevelNodes.add(it)}
                node.right?.let {nextLevelNodes.add(it)}
            }

            result.add(currentLevelList.next!!)
            currentLevelNodes = nextLevelNodes
        }

        return result
    }
}


class ListOfDepthsTest {

    @ Test
    fun `empty tree`() {
        val tree = null
        val expectedLists = emptyList < ListNode > ()
        assertEquals(expectedLists, ListOfDepths(tree).createListsOfDepths())
    }

    @ Test
    fun `one node tree`() {
        val tree = TreeNode(4)
        val expectedLists = listOf(ListNode(4))
        assertEquals(expectedLists, ListOfDepths(tree).createListsOfDepths())
    }

    @ Test
    fun `three level tree`() {
        val tree = TreeNode(
            1,
            TreeNode(
                2,
                TreeNode(4),
                TreeNode(5)
            ),
            TreeNode(
                3,
                TreeNode(6),
                TreeNode(7)
            )
        )

        val expectedLists = listOf(
            ListNode(1),
            ListNode(2).apply {next=ListNode(3)},
            ListNode(4).apply {next=ListNode(5).apply {next=ListNode(6).apply {next=ListNode(7)}}}
        )

        assertEquals(expectedLists, ListOfDepths(tree).createListsOfDepths())
    }
}
