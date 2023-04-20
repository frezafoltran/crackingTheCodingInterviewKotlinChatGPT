class GraphNode(val value: Int) {
   val neighbors: MutableList<GraphNode> = mutableListOf()
}

class RouteBetweenNodes {
   fun hasRoute(start: GraphNode, end: GraphNode): Boolean {
       val visited = mutableSetOf<GraphNode>()
       val toVisit = mutableListOf<GraphNode>()
       toVisit.add(start)

       while(toVisit.isNotEmpty()) {
           val currNode = toVisit.removeAt(0)
           if(currNode == end) {
               return true
           }
           visited.add(currNode)
           for(neighbor in currNode.neighbors) {
               if(!visited.contains(neighbor)) {
                   toVisit.add(neighbor)
               }
           }
       }
       return false
   }
}

class RouteBetweenNodesTest {
   private val node0 = GraphNode(0)
   private val node1 = GraphNode(1)
   private val node2 = GraphNode(2)
   private val node3 = GraphNode(3)
   private val node4 = GraphNode(4)

   init {
       node0.neighbors.add(node1)
       node1.neighbors.add(node2)
       node2.neighbors.add(node0)
       node2.neighbors.add(node3)
       node3.neighbors.add(node2)
       node4.neighbors.add(node3)
   }

   @Test
   fun `has route between nodes`() {
       assertTrue(RouteBetweenNodes().hasRoute(node0, node3))
       assertTrue(RouteBetweenNodes().hasRoute(node1, node0))
       assertFalse(RouteBetweenNodes().hasRoute(node3, node1))
       assertTrue(RouteBetweenNodes().hasRoute(node4, node3))
   }
}