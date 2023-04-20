Class for Minesweeper algorithm:

class Minesweeper(val numRows: Int, val numCols: Int, val numBombs: Int) {

   private val board: Array<IntArray> = Array(numRows) { IntArray(numCols) }

   fun initialize() {
       // randomly place bombs
       var bombsPlaced = 0
       while (bombsPlaced < numBombs) {
           val row = (0 until numRows).random()
           val col = (0 until numCols).random()
           if (board[row][col] != -1) {
               board[row][col] = -1
               bombsPlaced++
           }
       }
       // fill in number cells
       for (i in 0 until numRows) {
           for (j in 0 until numCols) {
               if (board[i][j] != -1) {
                   var count = 0
                   for (row in -1..1) {
                       for (col in -1..1) {
                           val r = i + row
                           val c = j + col
                           if (r in 0 until numRows && c in 0 until numCols && board[r][c] == -1) {
                               count++
                           }
                       }
                   }
                   board[i][j] = count
               }
           }
       }
   }

   fun printBoard() {
       for (i in 0 until numRows) {
           for (j in 0 until numCols) {
               print(if (board[i][j] == -1) "* " else "${board[i][j]} ")
           }
           println()
       }
   }

   fun play(row: Int, col: Int): Boolean {
       if (board[row][col] == -1) {
           return false
       }
       val queue = LinkedList<Pair<Int, Int>>()
       queue.add(row to col)
       val visited = Array(numRows) { BooleanArray(numCols) }
       while (queue.isNotEmpty()) {
           val (r, c) = queue.pop()
           if (!visited[r][c]) {
               visited[r][c] = true
               if (board[r][c] == 0) {
                   for (row in -1..1) {
                       for (col in -1..1) {
                           val rr = r + row
                           val cc = c + col
                           if (rr in 0 until numRows && cc in 0 until numCols) {
                               queue.add(rr to cc)
                           }
                       }
                   }
               }
           }
       }
       return visited.all { it.all { visited } }
   }

}

Class for Minesweeper test:

class MinesweeperTest {

   @Test
   fun `play game without losing`() {
       val minesweeper = Minesweeper(10, 10, 10)
       minesweeper.initialize()
       minesweeper.printBoard()
       val result = minesweeper.play(4, 4)
       assertTrue(result)
   }

   @Test
   fun `play game with losing`() {
       val minesweeper = Minesweeper(10, 10, 10)
       minesweeper.initialize()
       val bombIndexes = mutableListOf<Pair<Int, Int>>()
       for (i in 0 until minesweeper.numRows) {
           for (j in 0 until minesweeper.numCols) {
               if (minesweeper.board[i][j] == -1) {
                   bombIndexes.add(i to j)
               }
           }
       }
       val randomBombIndex = bombIndexes.random()
       val result = minesweeper.play(randomBombIndex.first, randomBombIndex.second)
       assertFalse(result)
   }

}