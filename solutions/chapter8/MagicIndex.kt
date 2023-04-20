class MagicIndex {
    // time complexity: O(log n)
    // space complexity: O(1)
    fun findMagicIndex(elements: IntArray): Int {
        var left = 0
        var right = elements.size - 1

        while (left <= right) {
            val mid = (left + right) / 2
            when {
                elements[mid] == mid -> return mid
                elements[mid] < mid -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }

    // time complexity: O(n)
    // space complexity: O(1)
    fun findMagicIndexWithDupes(elements: IntArray): Int {
        var index = 0
        while (index < elements.size) {
            if (elements[index] == index) {
                return index
            }
            index = maxOf(elements[index], index + 1)
        }
        return -1
    }
}


class MagicIndexTest {

    private val magicIndex = MagicIndex()

    @ Test
    fun `find magic index in sorted array`() {
        val elements = intArrayOf(-10, -5, 0, 3, 7)
        val expected = 3
        val result = magicIndex.findMagicIndex(elements)
        assertEquals(expected, result)
    }

    @ Test
    fun `find magic index in sorted array with dupes`() {
        val elements = intArrayOf(-10, -5, 2, 2, 2, 3, 7)
        val expected = 2
        val result = magicIndex.findMagicIndexWithDupes(elements)
        assertEquals(expected, result)
    }

    @ Test
    fun `no magic index found in sorted array`() {
        val elements = intArrayOf(-10, -5, 0, 2, 7)
        val expected = -1
        val result = magicIndex.findMagicIndex(elements)
        assertEquals(expected, result)
    }

    @ Test
    fun `no magic index found in array with dupes`() {
        val elements = intArrayOf(-10, -5, 2, 3, 3, 3, 7)
        val expected = -1
        val result = magicIndex.findMagicIndexWithDupes(elements)
        assertEquals(expected, result)
    }
}
