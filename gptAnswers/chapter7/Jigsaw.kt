class JigsawPiece(val edges: List<Edge>) {
   var rotatedEdges: List<Edge>? = null

   init {
       rotatedEdges = edges.toMutableList().apply { rotate() }
   }

   private fun MutableList<Edge>.rotate() {
       for(i in 1..3){
           add(0, removeLast())
           if(last().type == EdgeType.INNER) last().flip()
       }
   }
}

class Edge(val type: EdgeType, var connectedTo: Edge? = null) {
   enum class EdgeType {
       INNER, OUTER
   }

   private var isFlipped: Boolean = false

   fun flip() {
       isFlipped = !isFlipped
       connectedTo?.flip()
   }

   fun fitsWith(other: Edge): Boolean {
       if(isFlipped != other.isFlipped){
           return type == other.type
       }
       return type != other.type
   }
}

class JigsawPuzzle(private val pieces: List<JigsawPiece>, val size: Int) {
   private val solution: Array<Array<JigsawPiece?>> = Array(size) { arrayOfNulls(size) }

   // time complexity: O(n^2), where n is the size of the puzzle
   // space complexity: O(n^2)
   fun solve(): Boolean {
       return solve(0, 0)
   }

   private fun solve(row: Int, col: Int): Boolean {
       if(col == size){
           col = 0
           row++
           if(row == size) return true
       }
       for(p in pieces){
           if(canPlace(p, row, col)){
               place(p, row, col)
               if(solve(row, col+1)) return true
               remove(p, row, col)
           }
       }
       return false
   }

   private fun canPlace(piece: JigsawPiece, row: Int, col: Int): Boolean {
       if(row == 0 && piece.edges.find { it.type == Edge.EdgeType.OUTER } != null) 
          return false
       if(col == 0 && piece.edges.find { it.type == Edge.EdgeType.INNER } != null)
          return false
       if(col > 0){
           val left = solution[row][col-1]?.let { it.edges[1] }
           if(!piece.edges[3].fitsWith(left!!)) return false
       }
       if(row > 0) {
           val top = solution[row-1][col]?.let { it.edges[2] }
           if(!piece.edges[0].fitsWith(top!!)) return false
       }
       return true
   }

   private fun place(piece: JigsawPiece, row: Int, col: Int) {
       solution[row][col] = piece
   }

   private fun remove(piece: JigsawPiece, row: Int, col: Int) {
       solution[row][col] = null
   }
}

class JigsawPuzzleTest {

   private val piece1 = JigsawPiece(listOf(
       Edge(Edge.EdgeType.INNER),
       Edge(Edge.EdgeType.OUTER),
       Edge(Edge.EdgeType.OUTER, null),
       Edge(Edge.EdgeType.OUTER)
   ))
   private val piece2 = JigsawPiece(listOf(
       Edge(Edge.EdgeType.INNER),
       Edge(Edge.EdgeType.OUTER),
       Edge(Edge.EdgeType.OUTER, null),
       Edge(Edge.EdgeType.OUTER)
   ))

   private val puzzle = JigsawPuzzle(listOf(piece1, piece2), 2)

   @Test
   fun `solve puzzle`() {
       assertTrue(puzzle.solve())
   }
}