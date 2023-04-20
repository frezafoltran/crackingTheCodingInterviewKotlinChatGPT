class RobotinaGrid {
  // time complexity: O(r*c)
  // space complexity: O(r*c) 
  fun findPath(grid: Array<BooleanArray>): List<Pair<Int, Int>>? {
    val path = mutableListOf<Pair<Int, Int>>()
    if (findPath(grid, grid.size - 1, grid[0].size - 1, path)) {
      path.add(0, Pair(0, 0))
      return path
    }
    return null
  }

  private fun findPath(
    grid: Array<BooleanArray>,
    row: Int,
    col: Int,
    path: MutableList<Pair<Int, Int>>
  ): Boolean {
    if (row < 0 || col < 0 || !grid[row][col])
      return false

    val isAtOrigin = row == 0 && col == 0

    if (isAtOrigin || findPath(grid, row - 1, col, path) || findPath(grid, row, col - 1, path)) {
      path.add(Pair(row, col))
      return true
    }
    return false
  }
}

class RobotinaGridTest {
  @Test
  fun `grid with path`() {
    val grid = arrayOf(
      booleanArrayOf(true, true, true, true),
      booleanArrayOf(true, true, true, true),
      booleanArrayOf(true, true, true, true),
      booleanArrayOf(false, true, true, true)
    )
    val expectedPath = listOf(
      Pair(0, 0),
      Pair(0, 1),
      Pair(0, 2),
      Pair(0, 3),
      Pair(1, 3),
      Pair(2, 3),
      Pair(3, 3),
    )
    assertEquals(expectedPath, RobotinaGrid().findPath(grid))
  }

  @Test
  fun `grid without path`() {
    val grid = arrayOf(
      booleanArrayOf(true, true, true, true),
      booleanArrayOf(true, false, true, true),
      booleanArrayOf(true, true, true, true),
      booleanArrayOf(false, true, true, true)
    )
    assertNull(RobotinaGrid().findPath(grid))
  }
}