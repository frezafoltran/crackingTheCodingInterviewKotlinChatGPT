class AnimalShelter {
   private val dogs = LinkedList<Animal>()
   private val cats = LinkedList<Animal>()
   private var order = 0

   fun enqueue(animal: Animal) {
       animal.order = order++
       if (animal is Dog) {
           dogs.add(animal)
       } else if (animal is Cat) {
           cats.add(animal)
       }
   }

   fun dequeueAny(): Animal? {
       if (dogs.isEmpty() && cats.isEmpty()) {
           return null
       } else if (dogs.isEmpty()) {
           return dequeueCat()
       } else if (cats.isEmpty()) {
           return dequeueDog()
       } else if (dogs.peek().order < cats.peek().order) {
           return dequeueDog()
       } else {
           return dequeueCat()
       }
   }

   fun dequeueDog(): Animal? {
       if (dogs.isEmpty()) {
           return null
       } else {
           return dogs.remove()
       }
   }

   fun dequeueCat(): Animal? {
       if (cats.isEmpty()) {
           return null
       } else {
           return cats.remove()
       }
   }
}

sealed class Animal(var order: Int)

class Dog(order: Int = 0) : Animal(order)

class Cat(order: Int = 0) : Animal(order)

class AnimalShelterTest {

   @Test
   fun `dequeue any`() {
       val animal1 = Dog()
       val animal2 = Cat()
       val animal3 = Dog()
       val animal4 = Cat()

       val shelter = AnimalShelter().apply {
           enqueue(animal1)
           enqueue(animal2)
           enqueue(animal3)
           enqueue(animal4)
       }

       val dequeued = shelter.dequeueAny()
       assertTrue(dequeued == animal1 || dequeued == animal2)
   }

   @Test
   fun `dequeue dog`() {
       val animal1 = Dog()
       val animal2 = Cat()
       val animal3 = Dog()
       val animal4 = Cat()

       val shelter = AnimalShelter().apply {
           enqueue(animal1)
           enqueue(animal2)
           enqueue(animal3)
           enqueue(animal4)
       }

       val dequeued = shelter.dequeueDog()
       assertEquals(dequeued, animal1)
   }

   @Test
   fun `dequeue cat`() {
       val animal1 = Dog()
       val animal2 = Cat()
       val animal3 = Dog()
       val animal4 = Cat()

       val shelter = AnimalShelter().apply {
           enqueue(animal1)
           enqueue(animal2)
           enqueue(animal3)
           enqueue(animal4)
       }

       val dequeued = shelter.dequeueCat()
       assertEquals(dequeued, animal2)
   }
}