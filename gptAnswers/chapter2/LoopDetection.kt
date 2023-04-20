class LoopDetection {
    class Node(var value: Int, var next: Node?)

    // Time complexity: O(n)
    // Space complexity: O(1)
    fun findLoopStart(head: Node?): Node? {
        var slow: Node? = head
        var fast: Node? = head

        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) {
                break
            }
        }

        // Check if there's no loop
        if (fast == null || fast.next == null) {
            return null
        }

        // Move slow to head, keep fast at meeting point
        slow = head

        // Advance slow and fast at same pace until they meet
        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }

        return slow
    }
}

class LoopDetectionTest {
    @Test
    fun `empty`() {
        val result = LoopDetection().findLoopStart(null)
        assertEquals(null, result)
    }

    @Test
    fun `no loop`() {
        val node1 = LoopDetection.Node(1, null)
        val node2 = LoopDetection.Node(2, node1)
        val node3 = LoopDetection.Node(3, node2)
        val node4 = LoopDetection.Node(4, node3)
        val node5 = LoopDetection.Node(5, node4)

        val result = LoopDetection().findLoopStart(node5)
        assertEquals(null, result)
    }

    @Test
    fun `has loop`() {
        val node1 = LoopDetection.Node(1, null)
        val node2 = LoopDetection.Node(2, node1)
        val node3 = LoopDetection.Node(3, node2)
        val node4 = LoopDetection.Node(4, node3)
        val node5 = LoopDetection.Node(5, node4)

        node1.next = node3 // creates loop: 5 -> 4 -> 3 -> 1 -> 5 -> ...

        val result = LoopDetection().findLoopStart(node5)
        assertEquals(node3, result)
    }
}