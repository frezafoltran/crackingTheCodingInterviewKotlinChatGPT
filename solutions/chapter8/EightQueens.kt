class EightQueens {
   // time complexity: O(8^n), where n is the size of the board ( in this case, n=8)
   // space complexity: O(n)

   private val board = Array(8) {IntArray(8)}

   fun printAllArrangements() {
       placeQueens(0)
   }

   private fun placeQueens(row: Int) {
       if (row == 8) {
           printBoard()
           return
       }

       for (col in 0 until 8) {
           if (isValid(row, col)) {
               board[row][col] = 1
               placeQueens(row + 1)
               board[row][col] = 0
           }
       }
   }

   private fun isValid(row: Int, col: Int): Boolean {
       for (i in 0 until row) {
           if (board[i][col] == 1) return false
           val diff = row - i
           if (col - diff >= 0 & & board[i][col - diff] == 1) return false
           if (col + diff <= 7 & & board[i][col + diff] == 1) return false
       }
       return true
   }

   private fun printBoard() {
       for (i in 0 until 8) {
           for (j in 0 until 8) {
               print("${board[i][j]} ")
           }
           println()
       }
       println()
   }
}


class EightQueensTest {

    @ Test
    fun `print all arrangements`() {
        EightQueens().printAllArrangements()
    }
}
