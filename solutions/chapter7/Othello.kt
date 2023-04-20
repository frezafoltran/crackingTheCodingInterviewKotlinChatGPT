//Class:


class Othello {
    private val board: Array < Array < Piece?>>
    private var blackScore: Int = 0
    private var whiteScore: Int = 0
    private var currentPlayer: Color
    enum class Color {
        BLACK,
        WHITE
    }

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT
    }

    init {
        board = Array(8) {Array < Piece?> (8) {null}}
        board[3][3] = Piece(Color.WHITE)
        board[4][4] = Piece(Color.WHITE)
        board[3][4] = Piece(Color.BLACK)
        board[4][3] = Piece(Color.BLACK)
        blackScore = 2
        whiteScore = 2
        currentPlayer = Color.BLACK
    }

    fun play(row: Int, column: Int) {
        val piece = Piece(currentPlayer)
        board[row][column] = piece
        updateScore(piece, row, column)
        for (d in Direction.values()) {
            searchAndFlip(d, piece, row, column)
        }
        switchPlayer()
    }

    fun hasValidMoves(): Boolean {
        for (r in 0 until 8) {
            for (c in 0 until 8) {
                if (isValidMove(r, c)) {
                    return true
                }
            }
        }
        return false
    }

    private fun isValidMove(row: Int, column: Int): Boolean {
        if (board[row][column] != null) {
            return false
        }
        val piece = Piece(currentPlayer)
        for (d in Direction.values()) {
            if (searchDirection(d, piece, row, column, true)) {
                return true
            }
        }
        return false
    }

    private fun updateScore(piece: Piece, row: Int, column: Int) {
        if (piece.color == Color.BLACK) {
            blackScore += 1
        } else {
            whiteScore += 1
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == Color.BLACK) Color.WHITE else Color.BLACK
    }

    private fun searchAndFlip(
//        direction: Direction,
//        piece: Piece,
//        row: Int,
//        column: Int
    ) {
        searchDirection(direction, piece, row, column, false)
    }

    private fun searchDirection(
//        direction: Direction,
//        piece: Piece,
//        row: Int,
//        column: Int,
//        findOpponent: Boolean
    ): Boolean {
        var r = row
        var c = column
        var foundOpponent = false
        while (true) {
            r += getDelta(direction).first
            c += getDelta(direction).second
            if (r < 0 | | r >= 8 | | c < 0 | | c >= 8) {
                break
            }
            val current = board[r][c]
            if (current == null) {
                break
            }
            if (current.color == piece.color) {
                if (foundOpponent) {
                    flipPieces(piece, row, column, direction, r, c)
                }
                return foundOpponent
            } else {
                foundOpponent = true
            }
        }
        return false
    }

    private fun flipPieces(
//        piece: Piece,
//        startRow: Int,
//        startColumn: Int,
//        direction: Direction,
//        endRow: Int,
//        endColumn: Int
    ) {
        var r = startRow
        var c = startColumn
        while (true) {
            r += getDelta(direction).first
            c += getDelta(direction).second
            if (r == endRow & & c == endColumn) {
                break
            }
            board[r][c] = piece
            updateScore(piece, r, c)
            val opponent = getOpponent(piece)
            updateScore(opponent, r, c)
        }
    }

    private fun getDelta(direction: Direction): Pair < Int, Int > =
    when(direction) {
        Direction.UP -> Pair(-1, 0)
        Direction.DOWN -> Pair(1, 0)
        Direction.LEFT -> Pair(0, -1)
        Direction.RIGHT -> Pair(0, 1)
        Direction.UP_LEFT -> Pair(-1, -1)
        Direction.UP_RIGHT -> Pair(-1, 1)
        Direction.DOWN_LEFT -> Pair(1, -1)
        Direction.DOWN_RIGHT -> Pair(1, 1)
    }

    private fun getOpponent(piece: Piece): Piece {
        return if (piece.color == Color.BLACK) {
            Piece(Color.WHITE)
        } else {
            Piece(Color.BLACK)
        }
    }
}


class OthelloTest {

    @ Test
    fun `new game`() {
        val othello = Othello()
        // assert board setup and score
        assertEquals(2, othello.blackScore)
        assertEquals(2, othello.whiteScore)
        assertEquals(Othello.Color.BLACK, othello.currentPlayer)
    }

    @ Test
    fun `play game`() {
        val othello = Othello()
        othello.play(2, 3)
        // assert board, score, currentPlayer
        othello.play(3, 2)
        // assert board, score, currentPlayer
    }

    @ Test
    fun `no valid moves`() {
        val othello = Othello()
        // make moves until no valid moves left
        assertFalse(othello.hasValidMoves())
    }

    @ Test
    fun `has valid moves`() {
        val othello = Othello()
        // make moves until at least one valid move left
        assertTrue(othello.hasValidMoves())
    }
}


class Piece(val color: Othello.Color)
