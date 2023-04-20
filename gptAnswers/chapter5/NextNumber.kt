class NextNumber(val number: Int) {
   // time complexity: O(n), where n is length of binary representation of number
   // space complexity: O(1)

   fun getNextSmallest(): Int {
       var c = number
       var c0 = 0
       var c1 = 0
       while (c and 1 == 1) {
           c1++
           c = c ushr 1
       }
       if (c == 0) {
           return -1
       }
       while (c and 1 == 0 && c != 0) {
           c0++
           c = c ushr 1
       }
       val p = c0 + c1
       var mask = -1 shl (p + 1)
       c = number and mask.inv()
       val tail = (1 shl (c1 + 1)) - 1
       c = c or tail shl (c0 - 1)
       return c
   }

   fun getNextLargest(): Int {
       var c = number
       var c0 = 0
       var c1 = 0
       while (c and 1 == 0 && c != 0) {
           c0++
           c = c ushr 1
       }
       while (c and 1 == 1) {
           c1++
           c = c ushr 1
       }
       if (c0 + c1 == 31 || c0 + c1 == 0) {
           return -1
       }
       val p = c0 + c1
       c = number or (1 shl p)
       val mask = (1 shl p) - 1
       c = c and mask.inv()
       val tail = (1 shl (c1 - 1)) - 1
       c = c or tail
       return c
   }
}

class NextNumberTest {

   @Test
   fun `getNextSmallest and getNextLargest`() {
       val nextNumber = NextNumber(54)
       assertEquals(51, nextNumber.getNextSmallest())
       assertEquals(57, nextNumber.getNextLargest())
   }
}