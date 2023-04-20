class Dominos {
    // time complexity: O(1)
    // space complexity: O(1)

    fun isPossible(): Boolean {
        // Counting the number of black and white squares
        var blackSquares = 0
        var whiteSquares = 0
        for (i in 0 until 8) {
            for (j in 0 until 8) {
                if ((i+j) % 2 == 0) {
                    whiteSquares++
                } else {
                    blackSquares++
                }
            }
        }
        // checking if the number of black and white squares are the same
        return blackSquares == whiteSquares
    }
}


class DominosTest {

    @ Test
    fun `test isPossible`() {
        val dominos = Dominos()
        assertTrue {dominos.isPossible()}
    }
}
