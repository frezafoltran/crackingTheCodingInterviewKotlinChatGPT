class BitInsertion {
   // time complexity: O(1)
   // space complexity: O(1)

   fun insertBitwise(n: Int, m: Int, i: Int, j: Int): Int {
       // create a mask to clear bits i through j in n
       var allOnes = -0x1
       val leftMask = allOnes shl (j + 1)
       val rightMask = (1 shl i) - 1
       val mask = leftMask or rightMask

       // clear bits i through j in n
       val nCleared = n and mask

       // shift m to fit into n
       val mShifted = m shl i

       // merge m and n
       return nCleared or mShifted
   }
}

class BitInsertionTest {

   @Test
   fun `test with example`() {
       val bitInsertion = BitInsertion()
       val n = 0b10000000000
       val m = 0b10011
       val i = 2
       val j = 6
       val expected = 0b10001001100
       assertEquals(expected, bitInsertion.insertBitwise(n, m, i, j))
   }
}