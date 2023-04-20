class CircularArray<T>(vararg items: T) {
   private var array: Array<Any?> = arrayOfNulls<Any?>(items.size)
   private var head = 0

   init {
       for (i in items.indices) {
           array[i] = items[i]
       }
   }

   fun rotate(shift: Int) {
       head = (head + shift) % array.size
   }

   @Suppress("UNCHECKED_CAST")
   operator fun get(index: Int): T {
       if (index < 0 || index >= array.size) {
           throw IndexOutOfBoundsException()
       }
       return array[(head + index) % array.size] as T
   }

   fun iterator() = object : Iterator<T> {
       private var currentIndex = 0

       override fun hasNext() = currentIndex < array.size

       override fun next() = get(currentIndex++)
   }
}

class CircularArrayTest {

   @Test
   fun `iteration test`() {
       val circularArray = CircularArray('a', 'b', 'c', 'd', 'e')
       var expected = "abcde"

       for (item in circularArray) {
           assertEquals(expected.first(), item)
           expected = expected.drop(1) + expected.first()
       }
   }

   @Test
   fun `rotation test`() {
       val circularArray = CircularArray(1, 2, 3, 4, 5)
       circularArray.rotate(2)

       assertEquals(3, circularArray[0])
       assertEquals(4, circularArray[1])
       assertEquals(5, circularArray[2])
       assertEquals(1, circularArray[3])
       assertEquals(2, circularArray[4])
   }

   @Test(expected = IndexOutOfBoundsException::class)
   fun `exception test`() {
       val circularArray = CircularArray(1, 2, 3, 4, 5)
       val item = circularArray[5]
   }
}