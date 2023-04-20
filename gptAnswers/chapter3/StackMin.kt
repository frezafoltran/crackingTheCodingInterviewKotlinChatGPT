class StackMin {

    private val stack = mutableListOf<Int>()
    private val minStack = mutableListOf<Int>()

    fun push(element: Int) {
        stack.add(element)
        if (minStack.isNotEmpty() && element >= minStack.last()) {
            minStack.add(minStack.last())
        } else {
            minStack.add(element)
        }
    }

    fun pop(): Int {
        minStack.removeAt(minStack.size - 1)
        return stack.removeAt(stack.size - 1)
    }

    fun min(): Int {
        return minStack.last()
    }
}

class StackMinTest {

    @Test
    fun `test push pop`() {
        val stackMin = StackMin()
        stackMin.push(5)
        stackMin.push(1)
        assertEquals(1, stackMin.pop())
        assertEquals(5, stackMin.pop())
        assertThrows<NoSuchElementException> {
            stackMin.pop()
        }
    }

    @Test
    fun `test min`() {
        val stackMin = StackMin()
        stackMin.push(5)
        assertEquals(5, stackMin.min())
        stackMin.push(1)
        assertEquals(1, stackMin.min())
        stackMin.push(3)
        assertEquals(1, stackMin.min())
        stackMin.pop()
        assertEquals(1, stackMin.min())
    }
}