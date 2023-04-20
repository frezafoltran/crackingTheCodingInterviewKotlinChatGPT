Algorithm:

1. Create a function called paintFill which takes four arguments - a 2D array (screen), a point (x, y), a new color (newColor) and the original color (oldColor)
2. If the point (x, y) is not within the bounds of the screen array, return
3. If the color at the given point is not equal to the old color, return
4. Set the color at the given point to the new color
5. Recursively call paintFill on the surrounding points (up, down, left, right)
6. Return the screen array

class PaintFill {

   // time complexity: O(n*m), where n is the number of rows and m is the number of columns
   // space complexity: O(n*m)

   fun paintFill(screen: Array<Array<Int>>, x: Int, y: Int, newColor: Int, oldColor: Int): Array<Array<Int>> {
       if(x < 0 || x >= screen.size || y < 0 || y >= screen[0].size) {
           return screen
       }
       if(screen[x][y] != oldColor) {
           return screen
       }
       screen[x][y] = newColor
       paintFill(screen, x + 1, y, newColor, oldColor)
       paintFill(screen, x - 1, y, newColor, oldColor)
       paintFill(screen, x, y + 1, newColor, oldColor)
       paintFill(screen, x, y - 1, newColor, oldColor)
       return screen
   }
}

class PaintFillTest {

   @Test
   fun `example test`() {
       val screen = arrayOf(
           arrayOf(1, 1, 1, 1),
           arrayOf(1, 1, 1, 0),
           arrayOf(1, 0, 1, 0),
           arrayOf(1, 0, 0, 1)
       )

       val expected = arrayOf(
           arrayOf(2, 2, 2, 2),
           arrayOf(2, 2, 2, 0),
           arrayOf(2, 0, 2, 0),
           arrayOf(2, 0, 0, 1)
       )

       PaintFill().apply {
           assertEquals(expected, paintFill(screen, 1, 1, 2, 1))
       }
   }
}