class LinkedListNode(var data: Int, var next: LinkedListNode?)


class Partition {
    // time complexity: O(n), where n is the length of linked list
    // space complexity: O(1)

    fun partition(head: LinkedListNode?, x: Int): LinkedListNode? {
        var smallerHead: LinkedListNode? = null
        var smallerTail: LinkedListNode? = null
        var greaterHead: LinkedListNode? = null
        var greaterTail: LinkedListNode? = null

        var node = head
        while (node != null) {
            if (node.data < x) {
                if (smallerHead == null) {
                    smallerHead = node
                    smallerTail = node
                } else {
                    smallerTail?.next = node
                    smallerTail = node
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = node
                    greaterTail = node
                } else {
                    greaterTail?.next = node
                    greaterTail = node
                }
            }
            node = node.next
        }
        smallerTail?.next = greaterHead
        greaterTail?.next = null
        return smallerHead ?: greaterHead
    }
}


class PartitionTest {

    @ Test
    fun `partition list with x in the middle`() {
        val node1 = LinkedListNode(3, null)
        val node2 = LinkedListNode(5, null)
        val node3 = LinkedListNode(8, null)
        val node4 = LinkedListNode(5, null)
        val node5 = LinkedListNode(10, null)
        val node6 = LinkedListNode(2, null)
        val node7 = LinkedListNode(1, null)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node7

        val partition = Partition()
        val head = partition.partition(node1, 5)

        assertEquals(3, head?.data)
        assertEquals(1, head?.next?.data)
        assertEquals(2, head?.next?.next?.data)
        assertEquals(10, head?.next?.next?.next?.data)
        assertEquals(5, head?.next?.next?.next?.next?.data)
        assertEquals(8, head?.next?.next?.next?.next?.next?.data)
        assertNull(head?.next?.next?.next?.next?.next?.next)
    }

    @ Test
    fun `partition list with x at the beginning`() {
        val node1 = LinkedListNode(1, null)
        val node2 = LinkedListNode(3, null)
        val node3 = LinkedListNode(2, null)
        val node4 = LinkedListNode(6, null)
        val node5 = LinkedListNode(4, null)
        val node6 = LinkedListNode(5, null)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6

        val partition = Partition()
        val head = partition.partition(node1, 3)

        assertEquals(1, head?.data)
        assertEquals(2, head?.next?.data)
        assertEquals(3, head?.next?.next?.data)
        assertEquals(6, head?.next?.next?.next?.data)
        assertEquals(4, head?.next?.next?.next?.next?.data)
        assertEquals(5, head?.next?.next?.next?.next?.next?.data)
        assertNull(head?.next?.next?.next?.next?.next?.next)
    }
}
