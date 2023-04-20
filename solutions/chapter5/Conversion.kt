class Conversion {
    // time complexity: O(log n), where n is the max of A and B
    // space complexity: O(1)

    fun getNumBitsToFlip(A: Int, B: Int): Int {
        var count = 0
        var diff = A xor B

        while (diff != 0) {
            count += diff and 1
            diff = diff shr 1
        }
        return count
    }
}


class ConversionTest {
    @ Test
    fun `example case 1`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(29, 15)
        assertEquals(2, numBits)
    }

    @ Test
    fun `example case 2`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(27, 20)
        assertEquals(4, numBits)
    }

    @ Test
    fun `same number`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(10, 10)
        assertEquals(0, numBits)
    }

    @ Test
    fun `one zero, one one`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(0, 1)
        assertEquals(1, numBits)
    }

    @ Test
    fun `one one, one zero`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(1, 0)
        assertEquals(1, numBits)
    }

    @ Test
    fun `large numbers`() {
        val conversion = Conversion()
        val numBits = conversion.getNumBitsToFlip(1000000, 16777216)
        assertEquals(12, numBits)
    }
}
