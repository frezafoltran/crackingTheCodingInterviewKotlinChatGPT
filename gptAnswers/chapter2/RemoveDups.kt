:

class RemoveDups {
   // time complexity: O(n)
   // space complexity: O(n)

   fun removeDuplicates(head: ListNode?): ListNode? {
       val set = mutableSetOf<Int>()
       var current = head
       var previous: ListNode? = null

       while (current != null) {
           if (set.contains(current.`val`)) {
               previous?.next = current.next
           } else {
               set.add(current.`val`)
               previous = current
           }
           current = current.next
       }
       return head
   }
}

class RemoveDupsTest {

   private val removeDups = RemoveDups()

   @Test
   fun `remove duplicates with buffer`() {
       val head = ListNode(1).apply {
           next = ListNode(2).apply {
               next = ListNode(3).apply {
                   next = ListNode(2)
               }
           }
       }

       val expected = ListNode(1).apply {
           next = ListNode(2).apply {
               next = ListNode(3)
           }
       }

       val result = removeDups.removeDuplicates(head)
       assertEquals(expected, result)
   }

   @Test
   fun `remove duplicates without buffer`() {
       val head = ListNode(1).apply {
           next = ListNode(2).apply {
               next = ListNode(3).apply {
                   next = ListNode(2)
               }
           }
       }

       val expected = ListNode(1).apply {
           next = ListNode(2).apply {
               next = ListNode(3)
           }
       }

       val result = removeDups.removeDuplicatesWithoutBuffer(head)
       assertEquals(expected, result)
   }
}