class BlueEyedIsland {
   // time complexity: O(n), where n is the number of people with blue eyes
   // space complexity: O(1)

   fun daysToLeave(blueEyed: Int): Int {
       var days = 0
       var remaining = blueEyed
       while (remaining > 0) {
           days++
           remaining--
       }
       return days
   }
}

class BlueEyedIslandTest {
   private val blueEyedIsland = BlueEyedIsland()

   @Test
   fun `one person with blue eyes`() {
       assertEquals(1, blueEyedIsland.daysToLeave(1))
   }

   @Test
   fun `two people with blue eyes`() {
       assertEquals(2, blueEyedIsland.daysToLeave(2))
   }

   @Test
   fun `three people with blue eyes`() {
       assertEquals(3, blueEyedIsland.daysToLeave(3))
   }

   @Test
   fun `ten people with blue eyes`() {
       assertEquals(10, blueEyedIsland.daysToLeave(10))
   }
}