class ListNode(var `val`: Int) {
    var next: ListNode? = null
}


class Palindrome {

    fun isPalindrome(head: ListNode?): Boolean {
        val values = mutableListOf < Int > ()
        var current = head
        while (current != null) {
            values.add(current.`val`)
            current = current.next
        }
        for (i in 0 until values.size / 2) {
            if (values[i] != values[values.size - 1 - i]) {
                return false
            }
        }
        return true
    }
}


class PalindromeTest {

    private val palindrome = Palindrome()

    @ Test
    fun `empty list`() {
        assertTrue(palindrome.isPalindrome(null))
    }

    @ Test
    fun `one node`() {
        assertTrue(palindrome.isPalindrome(ListNode(1)))
    }

    @ Test
    fun `two nodes is palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(1)
        assertTrue(palindrome.isPalindrome(head))
    }

    @ Test
    fun `two nodes is not palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(2)
        assertFalse(palindrome.isPalindrome(head))
    }

    @ Test
    fun `odd number of nodes is palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(2)
        head.next!!.next!!.next!!.next = ListNode(1)
        assertTrue(palindrome.isPalindrome(head))
    }

    @ Test
    fun `odd number of nodes is not palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(4)
        head.next!!.next!!.next!!.next = ListNode(5)
        assertFalse(palindrome.isPalindrome(head))
    }

    @ Test
    fun `even number of nodes is palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(2)
        head.next!!.next!!.next = ListNode(1)
        assertTrue(palindrome.isPalindrome(head))
    }

    @ Test
    fun `even number of nodes is not palindrome`() {
        val head = ListNode(1)
        head.next = ListNode(2)
        head.next!!.next = ListNode(3)
        head.next!!.next!!.next = ListNode(3)
        head.next!!.next!!.next!!.next = ListNode(5)
        assertFalse(palindrome.isPalindrome(head))
    }
}
