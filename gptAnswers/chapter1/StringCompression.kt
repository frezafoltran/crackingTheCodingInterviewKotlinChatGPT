:

class StringCompression {
   // time complexity: O(n), where n is the length of string
   // space complexity: O(n)

   fun compress(str: String): String {
       var compressed = StringBuilder()
       var count = 1
       for(i in 0 until str.length-1) {
           if(str[i] == str[i+1]) {
               count++
           } else {
               compressed.append(str[i])
               compressed.append(count)
               count = 1
           }
       }
       compressed.append(str[str.length-1])
       compressed.append(count)
       return if(compressed.length < str.length) compressed.toString() else str
   }
}

class StringCompressionTest {

   private val stringCompression = StringCompression()

   @Test
   fun `compress string`() {
       assertEquals("a2b1c5a3", stringCompression.compress("aabcccccaaa"))
       assertEquals("a2b1c1d1", stringCompression.compress("aabcd"))
       assertEquals("abcdefg", stringCompression.compress("abcdefg"))
   }
}