class FlipBitToWin {

    // time complexity: O(b), where b is the number of bits in n
    // space complexity: O(1)
    fun longestOnesSeq(n: Int): Int {
        var maxLen = 0
        var curLen = 0
        var prevLen = 0

        var num = n
        while (num != 0) {
            if (num and 1 == 1) {
                curLen++
            } else {
                prevLen = if (num and 2 == 0) {
                    0 // current bit and next bit are both 0
                } else {
                    curLen // current bit is 0 but next bit is 1
                }
                curLen = 0
            }

            maxLen = maxOf(maxLen, prevLen + curLen + 1)
            num = num ushr 1
        }

        return maxLen
    }
}


class FlipBitToWinTest {

    private val solution = FlipBitToWin()

    @ Test
    fun `longest ones sequence`() {
        assertEquals(8, solution.longestOnesSeq(1775))
        assertEquals(2, solution.longestOnesSeq(22221))
        assertEquals(3, solution.longestOnesSeq(35))
        assertEquals(2, solution.longestOnesSeq(9))
        assertEquals(1, solution.longestOnesSeq(0))
        assertEquals(32, solution.longestOnesSeq(-1))
    }
}
