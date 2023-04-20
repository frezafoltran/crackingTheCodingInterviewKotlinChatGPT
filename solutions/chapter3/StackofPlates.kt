class SetOfStacks(private val capacity: Int) {
    private val stacks: MutableList < Stack < Int >> = mutableListOf(Stack())

    fun push(value: Int) {
        if (stacks.last().size == capacity) {
            stacks.add(Stack())
        }
        stacks.last().push(value)
    }

    fun pop(): Int? {
        if (stacks.isEmpty()) {
            return null
        }
        val value = stacks.last().pop()
        if (stacks.last().isEmpty()) {
            stacks.removeAt(stacks.lastIndex)
        }
        return value
    }

    fun popAt(index: Int): Int? {
        if (index < 0 | | index >= stacks.size) {
            return null
        }
        val value = stacks[index].pop()
        if (stacks[index].isEmpty()) {
            stacks.removeAt(index)
        }
        return value
    }

}


class SetOfStacksTest {
    @ Test
    fun `push and pop`() {
        val setOfStacks = SetOfStacks(2)
        setOfStacks.push(1)
        setOfStacks.push(2)
        setOfStacks.push(3)
        setOfStacks.push(4)
        assertEquals(4, setOfStacks.pop())
        assertEquals(3, setOfStacks.pop())
        assertEquals(2, setOfStacks.pop())
        assertEquals(1, setOfStacks.pop())
    }

    @ Test
    fun `popAt`() {
        val setOfStacks = SetOfStacks(2)
        setOfStacks.push(1)
        setOfStacks.push(2)
        setOfStacks.push(3)
        setOfStacks.push(4)
        assertEquals(2, setOfStacks.popAt(0))
        assertEquals(4, setOfStacks.pop())
        assertEquals(3, setOfStacks.pop())
        assertEquals(1, setOfStacks.pop())
    }
}
