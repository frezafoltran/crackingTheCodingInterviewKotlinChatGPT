class MyQueue<T> {

    private val stackIn = Stack<T>()
    private val stackOut = Stack<T>()

    fun enqueue(element: T) {
        stackIn.push(element)
    }

    fun dequeue(): T? {
        if (stackOut.empty()) {
            while (!stackIn.empty()) {
                stackOut.push(stackIn.pop())
            }
        }
        return if (stackOut.empty()) {
            null
        } else {
            stackOut.pop()
        }
    }

    fun isEmpty(): Boolean {
        return stackIn.empty() && stackOut.empty()
    }
}

class MyQueueTest {

    @Test
    fun testEnqueueAndDequeue() {
        val queue = MyQueue<Int>()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        assertEquals(1, queue.dequeue())
        assertEquals(2, queue.dequeue())
        queue.enqueue(4)
        assertEquals(3, queue.dequeue())
        assertEquals(4, queue.dequeue())
    }

    @Test
    fun testIsEmpty() {
        val queue = MyQueue<Int>()
        assertTrue(queue.isEmpty())
        queue.enqueue(1)
        assertFalse(queue.isEmpty())
        queue.dequeue()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun testDequeueEmpty() {
        val queue = MyQueue<Int>()
        assertNull(queue.dequeue())
    }
}