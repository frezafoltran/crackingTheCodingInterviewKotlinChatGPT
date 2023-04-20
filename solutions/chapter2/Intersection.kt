class Intersection {
    // time complexity: O(n)
    // space complexity: O(1)
    fun findIntersection(headA: ListNode?, headB: ListNode?): ListNode? {
        if (headA == null | | headB == null) return null

        var pA = headA
        var pB = headB

        while (pA != pB) {
            pA = if (pA == null) headB else pA.next
            pB = if (pB == null) headA else pB.next
        }

        return pA
    }
}


class IntersectionTest {
    @ Test
    fun `no intersection`() {
        val listA = ListNode(1)
        listA.next = ListNode(2)
        listA.next!!.next = ListNode(3)
        listA.next!!.next!!.next = ListNode(4)

        val listB = ListNode(9)
        listB.next = ListNode(8)
        listB.next!!.next = ListNode(7)

        assertNull(Intersection().findIntersection(listA, listB))
    }

    @ Test
    fun `same list`() {
        val list = ListNode(1)
        list.next = ListNode(2)
        list.next!!.next = ListNode(3)
        list.next!!.next!!.next = ListNode(4)

        assertEquals(list, Intersection().findIntersection(list, list))
    }

    @ Test
    fun `intersect at middle`() {
        val listA = ListNode(1)
        listA.next = ListNode(2)

        val intersection = ListNode(3)
        listA.next!!.next = intersection
        intersection.next = ListNode(4)

        val listB = ListNode(9)
        listB.next = ListNode(8)
        listB.next!!.next = intersection

        assertEquals(intersection, Intersection().findIntersection(listA, listB))
    }

    @ Test
    fun `intersect at end`() {
        val listA = ListNode(1)
        listA.next = ListNode(2)
        listA.next!!.next = ListNode(3)

        val intersection = ListNode(4)
        listA.next!!.next!!.next = intersection

        val listB = ListNode(9)
        listB.next = ListNode(8)
        listB.next!!.next = intersection

        assertEquals(intersection, Intersection().findIntersection(listA, listB))
    }
}
