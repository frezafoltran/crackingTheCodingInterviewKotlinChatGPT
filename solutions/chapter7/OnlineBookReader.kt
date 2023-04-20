class OnlineBookReader {
    private val books = mutableListOf < Book > ()
    private var currentBook: Book? = null
    private var currentPage: Int = 0

    fun addBook(book: Book) {
        books.add(book)
    }

    fun openBook(book: Book) {
        currentBook = book
        currentPage = 1
    }

    fun turnPageForward() {
        if (currentBook != null & & currentPage < currentBook!!.numPages) {
            currentPage++
        }
    }

    fun turnPageBackward() {
        if (currentBook != null & & currentPage > 1) {
            currentPage--
        }
    }

    fun getCurrentPage(): Page? {
        return currentBook?.getPage(currentPage)
    }

}


data class Book(val id: Int, val title: String, val author: String, val numPages: Int){
    private val pages = mutableListOf < Page > ()

    init{
        for (i in 1..numPages){
            val content = "Page content for page $i in $title"
            pages.add(Page(i, content))
        }
    }

    fun getPage(pageNum: Int): Page?{
        return pages.find {it.pageNum == pageNum}
    }
}

data class Page(val pageNum: Int, val content: String)


class OnlineBookReaderTest {

    @ Test
    fun `open and read book`() {
        val book = Book(1, "The Adventures of Sherlock Holmes", "Arthur Conan Doyle", 221)
        val reader = OnlineBookReader()
        reader.addBook(book)
        reader.openBook(book)
        assertEquals(1, reader.getCurrentPage()?.pageNum)
        reader.turnPageForward()
        assertEquals(2, reader.getCurrentPage()?.pageNum)
        reader.turnPageBackward()
        assertEquals(1, reader.getCurrentPage()?.pageNum)
    }

}
