Class:

class Jukebox {
   private val songs: MutableList<String> = mutableListOf() // list of available songs
   
   fun addSong(song: String) {
       songs.add(song)
   }
   
   fun selectSong(index: Int): String? {
       return if(index in 1..songs.size) {
           songs[index-1]
       } else {
           null
       }
   }
   
   fun shuffle(): String? {
       return if(songs.isNotEmpty()) {
           songs.shuffled()[0]
       } else {
           null
       }
   }
}

Test class:

class JukeboxTest {

   @Test
   fun `single song selection`() {
       val jukebox = Jukebox().apply {
           addSong("Bohemian Rhapsody")
       }
       assertEquals("Bohemian Rhapsody", jukebox.selectSong(1))
   }
   
   @Test
   fun `multiple song selection`() {
       val jukebox = Jukebox().apply {
           addSong("Shape of You")
           addSong("Despacito")
           addSong("Uptown Funk")
       }
       assertEquals("Shape of You", jukebox.selectSong(1))
       assertEquals("Despacito", jukebox.selectSong(2))
       assertEquals("Uptown Funk", jukebox.selectSong(3))
       assertNull(jukebox.selectSong(4))
   }
   
   @Test
   fun `shuffle songs`() {
       val jukebox = Jukebox().apply {
           addSong("Sugar")
           addSong("Closer")
           addSong("Hello")
           addSong("Happy")
       }
       val shuffledSong = jukebox.shuffle()
       assertTrue(shuffledSong in listOf("Sugar", "Closer", "Hello", "Happy"))
   }
}