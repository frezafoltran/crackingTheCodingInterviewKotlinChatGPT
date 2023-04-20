class PairwiseSwap {
    fun pairwiseSwap(num: Int): Int {
        return ((num and 0xaaaaaaaa.toInt()) ushr 1) or ((num and 0x55555555.toInt()) shl 1)
    }
}


class PairwiseSwapTest {

    @ Test
    fun `pairwise swap`() {
        val pairwiseSwap = PairwiseSwap()
        assertEquals(10, pairwiseSwap.pairwiseSwap(5))
        assertEquals(3, pairwiseSwap.pairwiseSwap(6))
        assertEquals(1431655765, pairwiseSwap.pairwiseSwap(-1431655766))
    }
}
