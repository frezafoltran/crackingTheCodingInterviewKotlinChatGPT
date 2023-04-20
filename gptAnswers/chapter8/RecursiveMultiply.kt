class RecursiveMultiply {
    // time complexity: O(log s), where s is the smaller number
    // space complexity: O(log s) due to call stack
    fun multiply(a: Int, b: Int): Int {
        val smaller = if (a < b) a else b
        val bigger = if (a < b) b else a
        return helper(smaller, bigger)
    }

    private fun helper(smaller: Int, bigger: Int): Int {
        if (smaller == 0) {
            return 0
        } else if (smaller == 1) {
            return bigger
        }

        val s = smaller shr 1
        val halfProd = helper(s, bigger)

        return if (smaller % 2 == 0) {
            halfProd + halfProd
        } else {
            halfProd + halfProd + bigger
        }
    }
}

class RecursiveMultiplyTest {

    @Test
    fun `multiply two numbers`() {
        val recMultiply = RecursiveMultiply()
        assertEquals(20, recMultiply.multiply(4, 5))
        assertEquals(48, recMultiply.multiply(6, 8))
        assertEquals(100, recMultiply.multiply(10, 10))
        assertEquals(999*1000, recMultiply.multiply(999, 1000))
    }

}