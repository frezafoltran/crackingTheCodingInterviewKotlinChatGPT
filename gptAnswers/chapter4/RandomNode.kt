class BinaryTreeNode<T>(val value: T) {
   var left: BinaryTreeNode<T>? = null
   var right: BinaryTreeNode<T>? = null
   var size: Int = 1

   fun insert(value: T) {
       if (value < this.value) {
           if (left == null) left = BinaryTreeNode(value)
           else left!!.insert(value)
       } else {
           if (right == null) right = BinaryTreeNode(value)
           else right!!.insert(value)
       }
       size++
   }

   fun find(value: T): BinaryTreeNode<T>? {
       if (value == this.value) return this
       return if (value < this.value) left?.find(value) else right?.find(value)
   }

   fun delete(value: T): BinaryTreeNode<T>? {
       val nodeToDelete = find(value) ?: return null
       // Left child is null
       if (nodeToDelete.left == null) {
           return nodeToDelete.right
       }
       // Right child is null
       if (nodeToDelete.right == null) {
           return nodeToDelete.left
       }
       // Both children exist
       var smallestNode = nodeToDelete.right!!
       while (smallestNode.left != null) {
           smallestNode = smallestNode.left!!
       }
       nodeToDelete.value = smallestNode.value
       nodeToDelete.right = nodeToDelete.right?.delete(smallestNode.value)
       return this
   }

   fun getRandomNode(): BinaryTreeNode<T>? {
       val randomIndex = (Math.random() * size).toInt()
       val leftSize = left?.size ?: 0
       return when {
           randomIndex == leftSize -> this
           randomIndex < leftSize -> left?.getRandomNode()
           else -> right?.getRandomNode()
       }
   }
}

class BinaryTreeNodeTest {
   @Test
   fun `getRandomNode returns a random node from the tree`() {
       val root = BinaryTreeNode(5)
       root.insert(2)
       root.insert(7)
       root.insert(1)
       root.insert(3)
       root.insert(6)
       root.insert(8)

       val nodeCounts = mutableMapOf<BinaryTreeNode<Int>, Int>()
       for (i in 1..10000) {
           val randomNode = root.getRandomNode()
           nodeCounts[randomNode] = nodeCounts.getOrDefault(randomNode, 0) + 1
       }

       for (node in listOf(root, root.left, root.right, root.left!!.left, root.left!!.right, root.right!!.left, root.right!!.right)) {
           assert(nodeCounts[node]!! > 0) { "Expected to see $node in random nodes, but it was never chosen" }
           val expectedProbability = 1.0 / 7
           val actualProbability = nodeCounts[node]!!.toDouble() / 10000
           assert(Math.abs(expectedProbability - actualProbability) < 0.02) { "Expected probability for $node was $expectedProbability but actual probability was $actualProbability" }
       }
   }
}