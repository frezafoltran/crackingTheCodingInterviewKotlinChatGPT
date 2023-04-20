class ZeroMatrix(val matrix: Array < IntArray > ) {

    fun zeroify() {
        // create sets to keep track of rows and columns to be zeroed
        val zeroRows = mutableSetOf < Int > ()
        val zeroCols = mutableSetOf < Int > ()

        // iterate through matrix and mark rows and columns to be zeroed
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i)
                    zeroCols.add(j)
                }
            }
        }

        // zero out marked rows
        zeroRows.forEach {row ->
                          for (j in matrix[row].indices) {
                              matrix[row][j] = 0
                          }
                          }

        // zero out marked columns
        zeroCols.forEach {col ->
                          for (i in matrix.indices) {
                              matrix[i][col] = 0
                          }
                          }
    }
}


class ZeroMatrixTest {

    @ Test
    fun `all zeros`() {
        val matrix = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )
        val expected = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )

        val zeroMatrix = ZeroMatrix(matrix)
        zeroMatrix.zeroify()
        assertArrayEquals(expected, zeroMatrix.matrix)
    }

    @ Test
    fun `some zeros`() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 0, 6),
            intArrayOf(7, 8, 9),
        )
        val expected = arrayOf(
            intArrayOf(1, 0, 3),
            intArrayOf(0, 0, 0),
            intArrayOf(7, 0, 9),
        )

        val zeroMatrix = ZeroMatrix(matrix)
        zeroMatrix.zeroify()
        assertArrayEquals(expected, zeroMatrix.matrix)
    }

    @ Test
    fun `no zeros`() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        )
        val expected = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        )

        val zeroMatrix = ZeroMatrix(matrix)
        zeroMatrix.zeroify()
        assertArrayEquals(expected, zeroMatrix.matrix)
    }
}
