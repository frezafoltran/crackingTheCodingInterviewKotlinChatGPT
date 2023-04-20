class ThreeStacks(val capacity: Int) {
    val array = arrayOfNulls < Int?> (capacity * 3)
    var stack1Top = -1
    var stack2Top = capacity - 1
    var stack3Top = capacity * 2 - 1

    fun push(stackNum: Int, value: Int) {
        when(stackNum) {
            1 -> {
                if (stack1Top + 1 >= capacity) throw StackOverflowException()
                array[++stack1Top] = value
            }
            2 -> {
                if (stack2Top + 1 >= capacity * 2) throw StackOverflowException()
                array[++stack2Top] = value
            }
            3 -> {
                if (stack3Top + 1 >= capacity * 3) throw StackOverflowException()
                array[++stack3Top] = value
            }
            else -> throw IllegalArgumentException("Invalid Stack Number")
        }
    }

    fun pop(stackNum: Int): Int {
        return when(stackNum) {
            1 -> {
                if (stack1Top < 0) throw StackUnderflowException()
                val popped = array[stack1Top]!!
                array[stack1Top--] = null
//                popped
            }
            2 -> {
                if (stack2Top < capacity) throw StackUnderflowException()
                val popped = array[stack2Top]!!
                array[stack2Top--] = null
//                popped
            }
            3 -> {
                if (stack3Top < capacity * 2) throw StackUnderflowException()
                val popped = array[stack3Top]!!
                array[stack3Top--] = null
//                popped
            }
            else -> throw IllegalArgumentException("Invalid Stack Number")
        }
    }

    fun peek(stackNum: Int): Int {
        return when(stackNum) {
            1 -> array[stack1Top] ?: throw StackUnderflowException()
            2 -> array[stack2Top] ?: throw StackUnderflowException()
            3 -> array[stack3Top] ?: throw StackUnderflowException()
            else -> throw IllegalArgumentException("Invalid Stack Number")
        }
    }

    fun isEmpty(stackNum: Int): Boolean {
        return when(stackNum) {
            1 -> stack1Top < 0
            2 -> stack2Top < capacity
            3 -> stack3Top < capacity * 2
            else -> throw IllegalArgumentException("Invalid Stack Number")
        }
    }

    fun isFull(stackNum: Int): Boolean {
        return when(stackNum) {
            1 -> stack1Top + 1 >= capacity
            2 -> stack2Top + 1 >= capacity * 2
            3 -> stack3Top + 1 >= capacity * 3
            else -> throw IllegalArgumentException("Invalid Stack Number")
        }
    }
}


class ThreeStacksTest {
    @ Test
    fun `push and pop`() {
        val stacks = ThreeStacks(2)
        stacks.push(1, 1)
        stacks.push(1, 2)
        stacks.push(2, 3)
        stacks.push(2, 4)
        stacks.push(3, 5)
        stacks.push(3, 6)
        assertEquals(2, stacks.pop(1))
        assertEquals(1, stacks.pop(1))
        assertEquals(4, stacks.pop(2))
        assertEquals(3, stacks.pop(2))
        assertEquals(6, stacks.pop(3))
        assertEquals(5, stacks.pop(3))
    }

    @ Test
    fun `peek`() {
        val stacks = ThreeStacks(2)
        stacks.push(1, 1)
        stacks.push(2, 2)
        stacks.push(3, 3)
        assertEquals(1, stacks.peek(1))
        assertEquals(2, stacks.peek(2))
        assertEquals(3, stacks.peek(3))
    }

    @ Test
    fun `isEmpty`() {
        val stacks = ThreeStacks(2)
        assertTrue(stacks.isEmpty(1))
        stacks.push(1, 1)
        assertFalse(stacks.isEmpty(1))
    }

    @ Test
    fun `isFull`() {
        val stacks = ThreeStacks(2)
        stacks.push(1, 1)
        stacks.push(1, 2)
        assertTrue(stacks.isFull(1))
        stacks.push(2, 3)
        stacks.push(2, 4)
        assertTrue(stacks.isFull(2))
        stacks.push(3, 5)
        stacks.push(3, 6)
        assertTrue(stacks.isFull(3))
    }

    @ Test(expected = StackOverflowException: : class)
    fun `push to full stack`() {
        val stacks = ThreeStacks(2)
        stacks.push(1, 1)
        stacks.push(1, 2)
        stacks.push(1, 3)
    }

    @ Test(expected = StackUnderflowException: : class)
    fun `pop from empty stack`() {
        val stacks = ThreeStacks(2)
        stacks.pop(1)
    }

    @ Test(expected = IllegalArgumentException: : class)
    fun `invalid stack number`() {
        val stacks = ThreeStacks(2)
        stacks.push(4, 1)
    }
}
