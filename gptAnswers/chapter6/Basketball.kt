class Basketball {

   /*
   The probability of winning game 1 is simply p. 
   The probability of winning game 2 is the probability of making 2 or 3 shots, which is p^2(1-p) + (1-p)^2p + p^3 = 3p^2 - 2p^3.
   So we should play game 1 if p > 3p^2 - 2p^3 and game 2 otherwise.
   */

   fun pickGame(p: Double): String {
       // set precision to account for rounding errors
       val precision = 0.000001
       return if (p > 3 * p * p - 2 * p * p * p + precision) "Game 1" else "Game 2"
   }
}

class BasketballTest {

   @Test
   fun `pick game`() {
       val b = Basketball()
       assertEquals("Game 1", b.pickGame(0.5))
       assertEquals("Game 1", b.pickGame(0.8))
       assertEquals("Game 2", b.pickGame(0.2))
       assertEquals("Game 2", b.pickGame(0.6))
   }
}