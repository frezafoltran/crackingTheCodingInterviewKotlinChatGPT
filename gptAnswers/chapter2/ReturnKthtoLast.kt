// Node class for linked list
class Node(var data: Int, var next: Node?)

// function to find kth to last element of singly linked list
// time complexity: O(n), where n is the length of list
// space complexity: O(1)
fun findKthToLast(head: Node?, k: Int): Node? {
    if (head == null || k < 1) return null
    
    var slow = head
    var fast = head
    
    // move fast pointer k-1 nodes ahead of slow pointer
    repeat(k-1) {
        fast = fast?.next
        // return null if k is greater than list length
        if (fast == null) return null
    }
    
    // move both pointers until fast reaches the end of list
    while(fast?.next != null) {
        slow = slow?.next
        fast = fast.next
    }
    
    return slow
}

// Test class
import org.junit.Test
import org.junit.Assert.assertEquals

class ReturnKthToLastTest {

    @Test
    fun `kth to last element exists`() {
        val head = Node(1, Node(2, Node(3, Node(4, Node(5, null)))))
        assertEquals(4, findKthToLast(head, 2)?.data)
    }

    @Test
    fun `kth to last element does not exist`() {
        val head = Node(1, Node(2, Node(3, Node(4, Node(5, null)))))
        assertEquals(null, findKthToLast(head, 6)?.data)
    }

    @Test
    fun `empty list`() {
        assertEquals(null, findKthToLast(null, 2)?.data)
    }
}```