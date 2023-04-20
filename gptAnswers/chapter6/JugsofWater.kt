class JugsofWater {
   // time complexity: O(1)
   // space complexity: O(1)

   fun measureFourQuarts(): Pair<Int, Int> {
       var bigJug = 5
       var smallJug = 0
       while (bigJug != 4) {
           if (bigJug == 3) {
               bigJug += 2
               smallJug -= 2
           } else {
               bigJug -= 1
               smallJug += 1
           }
       }
       return Pair(bigJug, smallJug)
   }
}

class JugsofWaterTest {

   @Test
   fun `measure four quarts`() {
       val jugsofWater = JugsofWater()

       val result = jugsofWater.measureFourQuarts()

       assertEquals(4, result.first)
       assertEquals(1, result.second)
   }
}