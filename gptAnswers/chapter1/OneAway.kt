class OneAway {
   // time complexity: O(n), where n is the length of the longer string
   // space complexity: O(1)

   fun isOneAway(str1: String, str2: String): Boolean {
       if (str1 == str2) return true

       val lengthDiff = Math.abs(str1.length - str2.length)
       if (lengthDiff > 1) return false

       var foundDiff = false
       var i = 0
       var j = 0

       while (i < str1.length && j < str2.length) {
           if (str1[i] != str2[j]) {
               if (foundDiff) return false
               foundDiff = true

               if (lengthDiff == 0) {
                   i++
                   j++
               } else if (str1.length > str2.length) {
                   i++
               } else {
                   j++
               }
           } else {
               i++
               j++
           }
       }

       return true
   }
}

class OneAwayTest {

   @Test
   fun `equal strings`() {
       val oneAway = OneAway()
       assertTrue(oneAway.isOneAway("hello", "hello"))
   }

   @Test
   fun `one char insert`() {
       val oneAway = OneAway()
       assertTrue(oneAway.isOneAway("hello", "helllo"))
   }

   @Test
   fun `one char remove`() {
       val oneAway = OneAway()
       assertTrue(oneAway.isOneAway("hello", "helo"))
   }

   @Test
   fun `one char replace`() {
       val oneAway = OneAway()
       assertTrue(oneAway.isOneAway("hello", "hallo"))
   }

   @Test
   fun `two char insert`() {
       val oneAway = OneAway()
       assertFalse(oneAway.isOneAway("hello", "hellllo"))
   }

   @Test
   fun `two char remove`() {
       val oneAway = OneAway()
       assertFalse(oneAway.isOneAway("hello", "heo"))
   }

   @Test
   fun `two char replace`() {
       val oneAway = OneAway()
       assertFalse(oneAway.isOneAway("hello", "halloo"))
   }
}