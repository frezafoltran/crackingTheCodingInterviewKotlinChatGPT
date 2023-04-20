class TripleStep {
   // time complexity: O(n)
   // space complexity: O(n)

   fun countWays(n: Int): Int {
       if(n < 0) return 0
       if(n == 0) return 1
       return countWays(n - 1) + countWays(n - 2) + countWays(n - 3)
   }
}

class TripleStepTest {

   private val tripleStep = TripleStep()

   @Test
   fun `n is negative`() {
       assertEquals(0, tripleStep.countWays(-5))
   }

   @Test
   fun `n is zero`() {
       assertEquals(1, tripleStep.countWays(0))
   }

   @Test
   fun `n is one`() {
       assertEquals(1, tripleStep.countWays(1))
   }

   @Test
   fun `n is two`() {
       assertEquals(2, tripleStep.countWays(2))
   }

   @Test
   fun `n is three`() {
       assertEquals(4, tripleStep.countWays(3))
   }

   @Test
   fun `n is five`() {
       assertEquals(13, tripleStep.countWays(5))
   }

   @Test
   fun `n is ten`() {
       assertEquals(274, tripleStep.countWays(10))
   }
}