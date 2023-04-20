:

class RotateMatrix {

   // time complexity: O(n^2), where n is the length of matrix row/col
   // space complexity: O(1)

   fun rotate(matrix: Array<Array<Int>>) {
       val n = matrix.size
       for (i in 0 until n / 2) {
           for (j in i until n - i - 1) {
               val temp = matrix[i][j]
               matrix[i][j] = matrix[n - 1 - j][i]
               matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j]
               matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i]
               matrix[j][n - 1 - i] = temp
           } 
       }
   }
}

class RotateMatrixTest {

   @Test
   fun `rotate matrix`() {
       val matrix = arrayOf(
           arrayOf(1, 2, 3, 4),
           arrayOf(5, 6, 7, 8),
           arrayOf(9, 10, 11, 12),
           arrayOf(13, 14, 15, 16)
       )
       val expected = arrayOf(
           arrayOf(13, 9, 5, 1),
           arrayOf(14, 10, 6, 2),
           arrayOf(15, 11, 7, 3),
           arrayOf(16, 12, 8, 4)
       )
       RotateMatrix().rotate(matrix)
       assertEquals(expected, matrix)
   }
}