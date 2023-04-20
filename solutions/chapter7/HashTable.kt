class HashTable < K, V > {
    private val table: Array < LinkedList < Node < K, V >> >
    private var size: Int = 0

    init {
        table = Array(100) {LinkedList < Node < K, V >> ()}
    }

    fun put(key: K, value: V) {
        val index = hash(key)
        val list = table[index]

        for (node in list) {
            if (node.key == key) {
                node.value = value
                return
            }
        }

        list.add(Node(key, value))
        size++
    }

    fun get(key: K): V? {
        val index = hash(key)
        val list = table[index]

        for (node in list) {
            if (node.key == key) {
                return node.value
            }
        }

        return null
    }

    fun remove(key: K) {
        val index = hash(key)
        val list = table[index]

        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val node = iterator.next()
            if (node.key == key) {
                iterator.remove()
                size--
                return
            }
        }
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    private fun hash(key: K) = (key.hashCode() % table.size + table.size) % table.size

    private data class Node < K, V > (val key: K, var value: V)
}


class HashTableTest {
    @ Test
    fun `put and get`() {
        val table = HashTable < String, Int > ()
        table.put("a", 1)
        table.put("b", 2)
        table.put("c", 3)

        assertEquals(1, table.get("a"))
        assertEquals(2, table.get("b"))
        assertEquals(3, table.get("c"))

        assertNull(table.get("d"))
    }

    @ Test
    fun `remove`() {
        val table = HashTable < String, Int > ()
        table.put("a", 1)
        table.put("b", 2)
        table.put("c", 3)
        table.remove("b")

        assertEquals(2, table.size)
        assertEquals(1, table.get("a"))
        assertNull(table.get("b"))
        assertEquals(3, table.get("c"))
    }

    @ Test
    fun `isEmpty`() {
        val table = HashTable < String, Int > ()
        assertTrue(table.isEmpty())

        table.put("a", 1)
        assertFalse(table.isEmpty())

        table.remove("a")
        assertTrue(table.isEmpty())
    }
}
