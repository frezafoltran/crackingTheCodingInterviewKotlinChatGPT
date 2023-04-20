class ListNode(var `val`: Int) {
    var next: ListNode? = null
}


class SumLists {
    // time complexity: O(max(n, m)) where n and m are the lengths of the two lists
    // space complexity: O(max(n, m)) because the new list can be at most as long as the longer input list

    fun sumLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var carry = 0
        var node1 = l1
        var node2 = l2
        var result: ListNode? = null
        var tail: ListNode? = null

        while (node1 != null | | node2 != null | | carry != 0) {
            val sum = (node1?.`val` ?: 0) + (node2?.`val` ?: 0) + carry
            val newNode = ListNode(sum % 10)
            carry = sum / 10

            if (result == null) {
                result = newNode
            } else {
                tail?.next = newNode
            }
            tail = newNode
            node1 = node1?.next
            node2 = node2?.next
        }
        return result
    }

    // time complexity: O(max(n, m)) where n and m are the lengths of the two lists
    // space complexity: O(max(n, m)) for the recursive call stack

    fun sumListsForward(l1: ListNode?, l2: ListNode?): ListNode? {
        var len1 = 0
        var len2 = 0
        var node1 = l1
        var node2 = l2

        while (node1 != null) {
            len1++
            node1 = node1.next
        }

        while (node2 != null) {
            len2++
            node2 = node2.next
        }

        if (len1 < len2) {
            repeat(len2 - len1) {
                l1?.let {
                    val newNode = ListNode(0)
                    newNode.next = it
                    l1 = newNode
                }
            }
        } else if (len2 < len1) {
            repeat(len1 - len2) {
                l2?.let {
                    val newNode = ListNode(0)
                    newNode.next = it
                    l2 = newNode
                }
            }
        }

        fun sumListsHelper(node1: ListNode?, node2: ListNode?): Pair < ListNode?, Int > {
            if (node1 == null) {
                return Pair(null, 0)
            }

            val(nextNode, carry) = sumListsHelper(node1.next, node2?.next)

            val sum = node1.`val` + (node2?.`val` ?: 0) + carry
            val newNode = ListNode(sum % 10)
            newNode.next = nextNode
            return Pair(newNode, sum / 10)
        }

        val(result, carry) = sumListsHelper(l1, l2)

        if (carry != 0) {
            val newNode = ListNode(carry)
            newNode.next = result
            return newNode
        }

        return result
    }
}


class SumListsTest {

    val sumLists = SumLists()

    @ Test
    fun `test sumLists`() {
        val l1 = ListNode(7).apply {
            next = ListNode(1).apply {
                next = ListNode(6)
            }
        }
        val l2 = ListNode(5).apply {
            next = ListNode(9).apply {
                next = ListNode(2)
            }
        }
        val expected = ListNode(2).apply {
            next = ListNode(1).apply {
                next = ListNode(9)
            }
        }
        assertEquals(expected, sumLists.sumLists(l1, l2))
    }

    @ Test
    fun `test sumListsForward`() {
        val l1 = ListNode(6).apply {
            next = ListNode(1).apply {
                next = ListNode(7)
            }
        }
        val l2 = ListNode(2).apply {
            next = ListNode(9).apply {
                next = ListNode(5)
            }
        }
        val expected = ListNode(9).apply {
            next = ListNode(1).apply {
                next = ListNode(2)
            }
        }
        assertEquals(expected, sumLists.sumListsForward(l1, l2))
    }
}
