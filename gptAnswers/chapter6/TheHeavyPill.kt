class TheHeavyPill {

   // time complexity: O(n), where n is the total number of pills
   // space complexity: O(1)

   fun findHeavyBottle(bottles: List<List<Double>>): Int {
       var totalWeight = 0.0
       var expectedWeight = 0.0

       for(i in bottles.indices){
           val bottle = bottles[i]
           var bottleWeight = 0.0
           for(pill in bottle){
               bottleWeight += pill
               totalWeight += pill
           }
           if(i == 0){
               expectedWeight += bottleWeight * 1.1
           } else {
               expectedWeight += bottleWeight * 1.0
           }
       }

       return ((totalWeight - expectedWeight) / 0.1).toInt()
   }

}

class TheHeavyPillTest {

   @Test
   fun `find heavy bottle`() {
       val bottleList = (1..20).map {
           val pills = mutableListOf<Double>()
           for(i in 1..10){
               pills.add(10.0)
           }
           if(it == 10) {
               pills.add(1.0)
           }
           pills
       }
       assertEquals(10, TheHeavyPill().findHeavyBottle(bottleList))
   }

}

Note: In the test class, we create 20 bottles each containing 10 gram pills except one bottle which contains 11 gram pills. We add the extra pill to the 10th bottle because Java is a 0-indexed language so the 10th bottle would be represented as index 9.