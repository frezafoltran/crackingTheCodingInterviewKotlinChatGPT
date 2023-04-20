class DeleteMiddleNode {
    // time complexity: O(1)
    // space complexity: O(1)
    fun deleteNode(node: ListNode?) {
        if(node == null || node.next == null) {
            return
        }
        node.value = node.next!!.value
        node.next = node.next!!.next
    }
}

class DeleteMiddleNodeTest {
    private val deleteMiddleNode = DeleteMiddleNode()

    @Test
    fun `delete middle node`() {
        val a = ListNode(1)
        val b = ListNode(2)
        val c = ListNode(3)
        val d = ListNode(4)
        val e = ListNode(5)
        val f = ListNode(6)

        a.next = b
        b.next = c
        c.next = d
        d.next = e
        e.next = f

        deleteMiddleNode.deleteNode(c)

        assertEquals(b.next?.value, d.value)
    }
}