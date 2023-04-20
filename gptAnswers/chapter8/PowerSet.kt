class PowerSet {

    fun <T> getAllSubsets(set: Set<T>): Set<Set<T>> {
        val subsets = mutableSetOf<Set<T>>()
        subsets.add(emptySet())

        for (element in set) {
            val newSubsets = mutableSetOf<Set<T>>()
            for (subset in subsets) {
                val newSubset = mutableSetOf<T>()
                newSubset.addAll(subset)
                newSubset.add(element)
                newSubsets.add(newSubset)
            }
            subsets.addAll(newSubsets)
        }

        return subsets
    }
}

class PowerSetTest {

    @Test
    fun `get all subsets of set`() {
        val set = setOf(1, 2, 3)
        val expectedSubsets = setOf(
            emptySet(),
            setOf(1),
            setOf(2),
            setOf(3),
            setOf(1, 2),
            setOf(1, 3),
            setOf(2, 3),
            setOf(1, 2, 3)
        )
        assertEquals(expectedSubsets, PowerSet().getAllSubsets(set))
    }
}