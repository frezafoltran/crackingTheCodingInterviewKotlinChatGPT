class URLify {

    fun urlify(str: CharArray, trueLength: Int) {
        var spaceCount = 0
        for (i in 0 until trueLength) {
            if (str[i] == ' ') spaceCount++
        }

        var index = trueLength + spaceCount * 2
        str[index] = '\u0000' // add null terminator

        for (i in trueLength - 1 downTo 0) {
            if (str[i] == ' ') {
                str[index - 1] = '0'
                str[index - 2] = '2'
                str[index - 3] = '%'
                index -= 3
            } else {
                str[index - 1] = str[i]
                index--
            }
        }
    }
}


class URLifyTest {

    private val urlify = URLify()

    @ Test
    fun `urlify example test`() {
        val input = "Mr John Smith ".toCharArray()
        urlify.urlify(input, 13)
        val expected = "Mr%20John%20Smith".toCharArray()
        assertArrayEquals(expected, input)
    }

    @ Test
    fun `urlify no space test`() {
        val input = "hello".toCharArray()
        urlify.urlify(input, 5)
        val expected = "hello".toCharArray()
        assertArrayEquals(expected, input)
    }

    @ Test
    fun `urlify all space test`() {
        val input = "         ".toCharArray()
        urlify.urlify(input, 3)
        val expected = "%20%20%20".toCharArray()
        assertArrayEquals(expected, input)
    }
}
