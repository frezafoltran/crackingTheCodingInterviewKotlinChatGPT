class Box(val width: Int, val height: Int, val depth: Int): Comparable<Box>{
   override fun compareTo(other: Box): Int {
       return (other.width * other.height * other.depth) - (width * height * depth)
   }
}

class StackOfBoxes{
   fun tallestPossibleStack(boxes: List<Box>): Int {
       val sortedBoxes = boxes.sortedDescending()
       val heights = IntArray(sortedBoxes.size)
       for(i in 0 until sortedBoxes.size) {
           var maxHeight = 0
           for(j in 0 until i) {
               if(sortedBoxes[j].width > sortedBoxes[i].width && sortedBoxes[j].height > sortedBoxes[i].height &&
                       sortedBoxes[j].depth > sortedBoxes[i].depth) {
                   maxHeight = maxOf(maxHeight, heights[j])
               }
           }
           heights[i] = maxHeight + sortedBoxes[i].height
       }
       return heights.max() ?: 0
   }
}

class StackOfBoxesTest{

   @Test
   fun `empty boxes`(){
       val stackOfBoxes = StackOfBoxes()
       assertEquals(0, stackOfBoxes.tallestPossibleStack(listOf()))
   }

   @Test
   fun `one box`(){
       val stackOfBoxes = StackOfBoxes()
       assertEquals(1, stackOfBoxes.tallestPossibleStack(listOf(Box(1, 1, 1))))
   }

   @Test
   fun `multiple boxes`(){
       val stackOfBoxes = StackOfBoxes()
       assertEquals(9, stackOfBoxes.tallestPossibleStack(listOf(Box(1, 1, 1),
               Box(2, 2, 2), Box(3, 3, 3))))
   }
}