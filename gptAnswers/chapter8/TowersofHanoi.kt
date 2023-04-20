class TowersOfHanoi {
    private val source: Stack<Int> = Stack()
    private val buffer: Stack<Int> = Stack()
    private val destination: Stack<Int> = Stack()

    init {
        // add disks to source stack in ascending order
        for (i in n downTo 1) {
            source.push(i)
        }
    }

    fun moveDisks() {
        move(n, source, destination, buffer)
    }

    private fun move(n: Int, source: Stack<Int>, destination: Stack<Int>, buffer: Stack<Int>) {
        if (n == 0) return

        // move n-1 disks from source to buffer using destination as buffer
        move(n - 1, source, buffer, destination)

        // move top disk from source to destination
        destination.push(source.pop())

        // move n-1 disks from buffer to destination using source as buffer
        move(n - 1, buffer, destination, source)
    }
}

class TowersOfHanoiTest {

    @Test
    fun `moveDisks should move all disks from source to destination`() {
        val n = 3
        val towersOfHanoi = TowersOfHanoi(n)
        towersOfHanoi.moveDisks()

        assertTrue(towersOfHanoi.source.isEmpty())
        assertTrue(towersOfHanoi.buffer.isEmpty())

        val expected = Stack<Int>().apply {
            for (i in n downTo 1) {
                push(i)
            }
        }
        assertEquals(expected, towersOfHanoi.destination)
    }
}